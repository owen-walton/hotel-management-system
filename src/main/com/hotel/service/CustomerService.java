/**
 * @author Owen Walton
 * Handles all business logic related to customers
 */
package main.com.hotel.service;

import main.com.hotel.dao.CustomerDAO;
import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.entity.Customer;

import java.util.List;

public class CustomerService
{
    private final CustomerDAO customerDAO;

    public CustomerService()
    {
        this.customerDAO = new CustomerDAO();
    }

    public Customer findCustomer(CustomerSearchDetails details)
    {
        List<Customer> searchResult = customerDAO.getByCustomerDetails(details);

        if(searchResult.isEmpty())
        {
            return new Customer(); // no args in constructor gives null and -1 default values
        }
        else if (searchResult.size() == 1)
        {
            return searchResult.get(0);
        }
        else
        {
            return null; // will allow the calling of the method to re try with more specific details
        }
    }

    public boolean customerMatchExists(CustomerSearchDetails details)
    {
        if (findCustomer(details) != null)
        {
            // findCustomer will return an object with id -1 if customer doesn't exist
            return !(findCustomer(details).getICustomerId() == -1);
        }
        else
        {
            // if null, multiple exist so return true
            return true;
        }
    }

    public boolean isOneSearchResult(CustomerSearchDetails details)
    {
        // assumes there is a search result (otherwise null error throws)
        return (findCustomer(details).getICustomerId() != -1);
    }

    public Customer createCustomer(Customer customer)
    {
        int newId = customerDAO.insert(customer);
        if(newId == -1)
        {
            return null;
        }

        customer.setICustomerId(newId);
        return customer;
    }

}