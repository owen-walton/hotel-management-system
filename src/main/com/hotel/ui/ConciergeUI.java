package main.com.hotel.ui;

import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.entity.Customer;
import main.com.hotel.model.entity.Room;
import main.com.hotel.role.access.ConciergeAccess;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
                    B) Edit/cancel a booking
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
        int numOfRooms;

        System.out.println("\nMake Booking:");
        if(!findCurrentCustomer())
        {
            createCustomer();
        }
        InputHelper.inputString("INPUT BOOKING DETAILS");
        numOfRooms = InputHelper.inputInteger(1, 20, "How many rooms do you want to book?");
        for (int i = 0; i < numOfRooms; i++)
        {
            if(!findRoomForCustomer()) // ROOMS MUST BE ADDED TO A MAP ONCE FOUND
            {
                return false;
            }
        }
        // ADD MAP TO ROOMBOOKING TABLE AND ADD BOOKING DETAILS TO BOOKING TABLE

        return true;
    }

    public void handleBooking()
    {

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
        String szSurname = null;
        if (InputHelper.inputYN("Do you know the customers DOB? (Y/N): "))
        {
            while (!isCorrect)
            {
                szSurname = InputHelper.inputString("Enter the customer's surname: ");

                boolean isDateInvalid = true;
                Date DOB = null;
                while(isDateInvalid)
                {
                    isDateInvalid = false;
                    System.out.println("Enter the customer's DOB. DD/MM/YYYY");
                    int day = InputHelper.inputInteger(1, 31, "Enter DD: ");
                    int month = InputHelper.inputInteger(1, 12, "Enter MM: ");
                    int year = InputHelper.inputInteger(1900, LocalDate.now().getYear(), "Enter YYYY: ");
                    try {
                        DOB = Date.valueOf(year + "-" + month + "-" + day);
                    } catch (IllegalArgumentException e) {
                        // catches dates like feb 30th which don't exist
                        isDateInvalid = true;
                        System.out.println("The date you entered is invalid.");
                    }
                }
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

    public void createCustomer()
    {
        // ENSURE TO SET CURRENT CUSTOMER VARIABLE TO THE NEW CUSTOMER
    }

    public boolean findRoomForCustomer()
    {
        InputHelper.inputString("INPUT ROOM DETAILS");
        List<Room> availableRooms = access.getAvailableRooms(); // pass in details

        if (availableRooms.isEmpty())
        {
            System.out.println("Sorry we don't have a room available that fits the requirements for that date");
            return false;
        }
        else if (availableRooms.size() == 1)
        {
            if (!InputHelper.inputYN("We have one room available that fits the requirements on that date\n"
                    + availableRooms.get(0)
                    + "\nIs this room okay? (Y/N): "))
            {
                return false;
            }
            // ADD ROOM NUMBER AND OCCUPANTS TO MAP
            return true;
        }
        else
        {
            if(InputHelper.inputYN("This is the cheapest available room that fits the requirements on that date\n"
                    + access.getCheapestRoom(availableRooms)
                    + "\nIs this room okay? (Y/N): "))
            {
                // ADD ROOM NUMBER AND OCCUPANTS TO MAP
                return true;
            }
            else
            {
                availableRooms.remove(access.getCheapestRoom(availableRooms));

                while(availableRooms.size() > 1)
                {
                    if(InputHelper.inputYN("This is the next cheapest available room that fits the requirements on that date\n"
                            + access.getCheapestRoom(availableRooms)
                            + "\nIs this room okay? (Y/N): "))
                    {
                        // ADD ROOM NUMBER AND OCCUPANTS TO MAP
                        return true;
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
                    // ADD ROOM NUMBER AND OCCUPANTS TO MAP
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
}
