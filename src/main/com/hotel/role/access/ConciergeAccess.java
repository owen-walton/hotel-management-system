package main.com.hotel.role.access;

import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.criteria.RoomDetails;
import main.com.hotel.model.entity.BookingResult;
import main.com.hotel.model.entity.Customer;
import main.com.hotel.model.entity.Room;
import main.com.hotel.service.BookingService;
import main.com.hotel.service.CustomerService;
import main.com.hotel.service.RoomService;

import java.sql.Date;
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

    // take in room details
    public List<Room> getAvailableRooms(Date startDate, int nights, int occupants, RoomDetails roomDetails)
    {
        return null;
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
}
