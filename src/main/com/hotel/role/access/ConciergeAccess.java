package main.com.hotel.role.access;

import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.entity.Customer;
import main.com.hotel.model.entity.Room;
import main.com.hotel.service.CustomerService;

import java.util.List;

public class ConciergeAccess
{
    private final CustomerService customerService;

    public ConciergeAccess()
    {
        this.customerService = new CustomerService();
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
    public List<Room> getAvailableRooms()
    {
        // pass in required details
        return null;
    }

    public Room getCheapestRoom(List<Room> rooms)
    {
        return null;
    }

    public void addBooking()
    {
        // handle both booking and room booking
    }
}
