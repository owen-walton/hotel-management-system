/**
 * @author Owen Walton
 */
package main.com.hotel.ui;

import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.criteria.RoomDetails;
import main.com.hotel.model.entity.BookingResult;
import main.com.hotel.model.entity.Customer;
import main.com.hotel.model.entity.Room;
import main.com.hotel.role.access.ConciergeAccess;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConciergeUI implements BaseUI
{
    private final ConciergeAccess access;
    private Customer currentCustomer;
    private boolean sessionActive;

    public ConciergeUI()
    {
        this.access = new ConciergeAccess();
        this.sessionActive = true;
        this.currentCustomer = null;
    }

    public void setSessionActive(boolean isSessionActive)
    {
        this.sessionActive = isSessionActive;
    }

    public void setCurrentCustomer(Customer customer)
    {
        this.currentCustomer = customer;
    }

    public boolean getSessionActive()
    {
        return this.sessionActive;
    }

    public Customer getCurrentCustomer()
    {
        return currentCustomer;
    }

    @Override
    public void run()
    {
        String input;

        while(getSessionActive())
        {
            input = InputHelper.inputLetterMultipleChoice(6, """
                    What would you like to do?
                    A) Make a booking
                    B) Edit/cancel/find a booking
                    C) Edit/delete customer details
                    D) Retrieve customer details
                    E) Access customer detail logs
                    F) Close program
                    Enter a letter:\s""");

            switch (input) {
                case "A" -> makeBooking();
                case "B" -> handleBooking();
                case "C" -> handleCustomerDetails();
                case "D" -> retrieveCustomerDetails();
                case "E" -> pullCustomerDetailLogs();
                case "F" -> setSessionActive(false);
                default -> throw new IllegalStateException("Unexpected value: " + input);
            }

        }
    }

    public boolean makeBooking()
    {
        int numOfRooms, totalCost = 0;
        Map<Integer, Integer> selectedRooms = new HashMap<>(); // room number and occupants

        System.out.println("\nMake Booking:");

        if (!signInCustomer())
        {
            return false;
        }

        // input booking data
        int iNights = InputHelper.inputInteger(1, 15, "Enter number of nights they would like to book for: ");
        Date startDate = InputHelper.inputDate(LocalDate.now(), LocalDate.now().plusYears(1), "Enter the date they would like to booking to start.");

        numOfRooms = InputHelper.inputInteger(1, 20, "How many rooms do you want to book? ");

        List<Integer> excludedRoomNumbers = new ArrayList<>(); // rooms that can't be offered

        // for every room required, run findRoomForCustomer
        for (int i = 0; i < numOfRooms; i++)
        {
            Map<Integer,Integer> temp = findRoomForCustomer(startDate, iNights, excludedRoomNumbers);
            if(temp == null || temp.isEmpty())
            {
                System.out.println("Booking cancelled.");
                return false;
            }
            else
            {
                selectedRooms.putAll(temp);

                // map is only length 1
                Map.Entry<Integer, Integer> entry = temp.entrySet().iterator().next();
                excludedRoomNumbers.add(entry.getKey()); // add room that was just selected to excluded rooms
                totalCost += access.getRoomCost(entry.getKey());
            }
        }

        totalCost = totalCost * iNights;
        // THIS IS WHERE PAYMENT IS HANDLED

        // add Booking details to booking table and add selectedRooms map to RoomBooking table
        BookingResult result = access.addBooking(getCurrentCustomer().getICustomerId(), startDate, iNights, selectedRooms);

        // confirm booking
        System.out.println("Booking confirmed. Here are the details:\nTotal cost: £" + totalCost +
                "\n" + result);
        return true;
    }

    public void handleBooking()
    {
        String input = InputHelper.inputLetterMultipleChoice(2, """
                What do you need access to bookings for?
                A) Customer request
                B) Admin
                Enter a letter:\s""");

        switch (input)
        {
            case "A" -> {
                if (!findCurrentCustomer())
                {
                    System.out.println("Customer account cannot be found so there are no bookings they can handle.");
                    return;
                }

                List<BookingResult> customerBookings = access.getFutureBookingsByCustomerId(getCurrentCustomer().getICustomerId());
                if(customerBookings.isEmpty())
                {
                    System.out.println("Customer has no bookings to handle.");
                }
                else
                {
                    String input2 = InputHelper.inputLetterMultipleChoice(3, """
                            What would they like to do:
                            A) Retrieve booking details
                            B) Edit booking details
                            C) Cancel booking""");

                    switch (input2)
                    {
                        case "A" -> {
                            System.out.println("Here are the booking details:\n" + selectBooking(customerBookings));
                            return;
                        }
                        case "B" -> {
                            // EDIT BOOKING DETAILS
                        }
                        case "C" -> {
                            // CURRENTLY ALLOWS CANCELLING OF ANY BOOKING FROM TODAY ONWARDS, POTENTIAL 3 DAY NO CANCELLATION IN FUTURE
                            BookingResult booking = selectBooking(customerBookings);
                            if (InputHelper.inputYN("Are you sure you want to delete the booking with details:\n" +
                                    booking + "\n(Enter Y/N): "))
                            {
                                // CONCIERGE SHOULD HANDLE REFUND AT THIS POINT
                                boolean deleted = access.deleteBooking(booking);
                                if (deleted)
                                {
                                    System.out.println("Booking successfully cancelled");
                                    return;
                                }
                                else
                                {
                                    System.out.println("Booking failed to be deleted.");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            case "B" -> {

            }
            default -> throw new IllegalStateException("Unexpected value: " + input);
        }
    }

    public void handleCustomerDetails()
    {

    }

    public void retrieveCustomerDetails()
    {

    }

    public void pullCustomerDetailLogs()
    {

    }

    public boolean findCurrentCustomer()
    {
        boolean isCorrect = false;
        CustomerSearchDetails details = null;
        String szSurname;
        if (InputHelper.inputYN("Do you know the customers DOB? (Y/N): "))
        {
            while (!isCorrect)
            {
                szSurname = InputHelper.inputString("Enter the customer's surname: ");
                Date DOB = InputHelper.inputDate(LocalDate.now().minusYears(120), LocalDate.now(), "Enter the customer's DOB.");

                if (DOB != null) // can't allow a search by just surname as that isn't secure
                {
                    details = new CustomerSearchDetails(szSurname, DOB, null, null);
                }

                isCorrect = InputHelper.inputYN("You entered:\n" + details + "Is this data correct? (Y/N): ");
            }

            if (!access.customerMatchExists(details))
            {
                return false; // if customer doesn't exist function will have returned
            }
            if(access.isOneSearchResult(details))
            {
                setCurrentCustomer(access.findCustomer(details));
                return true;
            }

            System.out.print("There are multiple results. ");
        }

        // now that multiple customers exists OR no DOB is given
        boolean bRetry = true;
        while (bRetry)
        {
            bRetry = false;
            szSurname = InputHelper.inputString("Enter the customer's surname: ");

            String input = InputHelper.inputLetterMultipleChoice(2, """
                    Would you like to enter the customer's:
                    A) Email
                    B) Phone Number
                    Enter a letter:\s""");
            switch (input)
            {
                case "A" -> details = new CustomerSearchDetails(
                        szSurname, null,
                        InputHelper.inputString("Enter the customer's email: "), null);
                case "B" -> details = new CustomerSearchDetails(
                        szSurname, null, null,
                        InputHelper.inputString("Enter the customer's phone number: 0"));
                default -> {return false;}
            }
            // phone number and email are unique already established so no need for unique checks
            if (!access.customerMatchExists(details))
            {
                // if entered correctly and not found, assume customer doesn't exist despite another having same surname and DOB
                bRetry = !(InputHelper.inputYN("The email/phone number did not exist, did you enter it correctly? (Y/N): "));
            }
        }

        if(access.customerMatchExists(details))
        {
            setCurrentCustomer(access.findCustomer(details));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean createCustomer()
    {
        System.out.println("\nEnter new customer details:");

        String surname = InputHelper.inputString("Surname: ");
        String firstName = InputHelper.inputString("First Name: ");
        String title = InputHelper.inputString("Title (e.g. Mr, Ms): ");
        Date dob = InputHelper.inputDate(LocalDate.now().minusYears(120), LocalDate.now(),"Enter DOB.");
        String houseNumber = InputHelper.inputString("House Number or Name: ");
        String streetName = InputHelper.inputString("Street Name: ");
        String city = InputHelper.inputString("City: ");
        String postcode = InputHelper.inputString("Postcode: ");

        String phoneNumber = "0" + InputHelper.inputString("Phone Number: 0");
        String email = InputHelper.inputString("Email Address: ");

        // if email or phone isn't unique
        if (access.customerMatchExists(new CustomerSearchDetails(null, null, email, null))
        || access.customerMatchExists(new CustomerSearchDetails(null, null, null, phoneNumber)))
        {
            System.out.println("The email or phone number you entered is already in our system... Cancelling operation.");
            return false;
        }

        // id is temporarily -1
        setCurrentCustomer(new Customer(-1, surname, firstName, title, dob,
                houseNumber, streetName, city, postcode,
                phoneNumber, email, true));

        // if customer not created currentCustomer = null
        Customer insertResult = access.createCustomer(getCurrentCustomer());
        if(insertResult != null)
        {
            setCurrentCustomer(insertResult);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Map<Integer, Integer> findRoomForCustomer(Date startDate, int nights, List<Integer> excludedRoomNumbers)
    {
        Map<Integer, Integer> roomNumberAndOccupants = new HashMap<>();
        int occupants = InputHelper.inputInteger(1, 3, "Enter how many occupants will stay in this room: ");
        RoomDetails roomDetails = InputHelper.inputRoomDetails();
        List<Room> availableRooms = access.getAvailableRooms(startDate, nights, occupants, roomDetails, excludedRoomNumbers);

        if (availableRooms.isEmpty())
        {
            System.out.println("Sorry we don't have a room available that fits the requirements for that date");
            return null;
        }
        else if (availableRooms.size() == 1)
        {
            if (!InputHelper.inputYN("We have one room available that fits the requirements on that date\n"
                    + availableRooms.get(0)
                    + "\nIs this room okay? (Y/N): "))
            {
                return null;
            }

            roomNumberAndOccupants.put(availableRooms.get(0).getIRoomNumber(), occupants);
            return roomNumberAndOccupants;
        }
        else
        {
            if(InputHelper.inputYN("This is the cheapest available room that fits the requirements on that date\n"
                    + access.getCheapestRoom(availableRooms)
                    + "\nIs this room okay? (Y/N): "))
            {
                roomNumberAndOccupants.put(access.getCheapestRoom(availableRooms).getIRoomNumber(), occupants);
                return roomNumberAndOccupants;
            }

            availableRooms.remove(access.getCheapestRoom(availableRooms));

            while(availableRooms.size() > 1)
            {
                if(InputHelper.inputYN("This is the next cheapest available room that fits the requirements on that date\n"
                        + access.getCheapestRoom(availableRooms)
                        + "\nIs this room okay? (Y/N): "))
                {
                    roomNumberAndOccupants.put(access.getCheapestRoom(availableRooms).getIRoomNumber(), occupants);
                    return roomNumberAndOccupants;
                }
                else
                {
                    availableRooms.remove(access.getCheapestRoom(availableRooms));
                }
            }

            if(InputHelper.inputYN("This is the last available room that fits the requirements on that date\n"
                    + access.getCheapestRoom(availableRooms)
                    + "\nIs this room okay? (Y/N): "))
            {
                roomNumberAndOccupants.put(access.getCheapestRoom(availableRooms).getIRoomNumber(), occupants);
                return roomNumberAndOccupants;
            }
            else
            {
                return null;
            }
        }
    }

    public boolean signInCustomer()
    {
        if(InputHelper.inputYN("Are they an existing customer? (Y/N): "))
        {
            // find customer if not create new customer and add to db
            if(!findCurrentCustomer())
            {
                return createCustomer();
            }
        }
        else
        {
            return createCustomer();
        }

        return true;
    }

    public BookingResult selectBooking(List<BookingResult> customerBookings)
    {
        BookingResult bookingResult;

        if (customerBookings.size() == 1)
        {
            bookingResult = customerBookings.get(0);
            System.out.println("Found 1 booking.");
        }
        else
        {
            StringBuilder promptBuilder = new StringBuilder("Which booking do you want access to? Select it by start date:\n");

            for (int i = 0; i < customerBookings.size(); i++) {
                char optionLetter = (char) ('A' + i);
                String dateString = customerBookings.get(i).booking().getStartDate().toString(); // format if needed
                promptBuilder.append(optionLetter)
                        .append(") ")
                        .append(dateString)
                        .append("\n");
            }

            promptBuilder.append("Enter a letter: ");
            String input3 = InputHelper.inputLetterMultipleChoice(customerBookings.size(), promptBuilder.toString());

            int index = input3.charAt(0) - 'A';

            bookingResult = customerBookings.get(index);

            System.out.println("Booking selected.");
        }
        return bookingResult;
    }
}
