/**
 * @author Owen Walton
 * Smart wrapper for service files so concierge doesn't have direct access to service logic
 */
package main.com.hotel.role.access;

import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.criteria.RoomDetails;
import main.com.hotel.model.entity.*;
import main.com.hotel.service.BookingService;
import main.com.hotel.service.CustomerService;
import main.com.hotel.service.RoomService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConciergeAccess
{
    private final CustomerService customerService;
    private final BookingService bookingService;
    private final RoomService roomService;

    public ConciergeAccess()
    {
        this.customerService = new CustomerService();
        this.bookingService = new BookingService();
        this.roomService = new RoomService();
    }

    public boolean customerMatchExists(CustomerSearchDetails details)
    {
        return customerService.customerMatchExists(details);
    }

    public boolean isOneSearchResult(CustomerSearchDetails details)
    {
        return customerService.isOneSearchResult(details);
    }

    public Customer findCustomer(CustomerSearchDetails details)
    {
        return customerService.findCustomer(details);
    }

    public List<Room> getAvailableRooms(Date startDate, int nights, int occupants, RoomDetails roomDetails, List<Integer> excludedRoomNumbers)
    {
        List<Room> matchingRooms = roomService.getMatchingRooms(occupants, roomDetails, excludedRoomNumbers);
        List<RoomBooking> busyRooms = bookingService.getOverlappingRoomBookings(startDate, nights);
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : matchingRooms)
        {
            boolean isBusy = false;

            int roomNumber = room.getIRoomNumber();

            for (RoomBooking busyRoom : busyRooms)
            {
                if (busyRoom.getIRoomNumber() == roomNumber)
                {
                    isBusy = true;
                    break;
                }
            }

            if (!isBusy)
            {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public Room getCheapestRoom(List<Room> rooms)
    {
        return roomService.getCheapestRoom(rooms);
    }

    public BookingResult addBooking(int customerId, Date startDate, int iNights, Map<Integer, Integer> selectedRooms)
    {
        return bookingService.addBooking(customerId, startDate, iNights, selectedRooms);
    }

    public Customer createCustomer(Customer customer)
    {
        return customerService.createCustomer(customer);
    }

    public boolean deleteCustomer(Customer customer)
    {
        boolean bSuccess = true;
        for (BookingResult booking : bookingService.getBookingsByCustomerId(customer.getICustomerId()))
        {
            bSuccess = bookingService.deleteBooking(booking);
            if(!bSuccess)
            {
                break;
            }
        }
        if(bSuccess)
        {
            bSuccess = customerService.deleteCustomer(customer);
        }
        return bSuccess;
    }

    public boolean updateCustomer(Customer customer)
    {
        return customerService.updateCustomer(customer);
    }

    public List<BookingResult> getFutureBookingsByCustomerId(int customerId)
    {
        return bookingService.getFutureBookingsByCustomerId(customerId);
    }

    public boolean deleteBooking(BookingResult bookingResult)
    {
        return bookingService.deleteBooking(bookingResult);
    }

    public int getRoomCost(int roomNumber)
    {
        return roomService.getRoomCost(roomNumber);
    }
}
