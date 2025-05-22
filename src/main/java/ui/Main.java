package main.java.ui;

import main.java.dao.BookingDAO;
import main.java.dao.CustomerDAO;
import main.java.dao.RoomDAO;
import main.java.model.Booking;
import main.java.model.Customer;
import main.java.model.Room;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        CustomerDAO customerDAO = new CustomerDAO();

        // Insert a customer
        Customer newCustomer = new Customer(-1, "Bloggs" , "Joe" , "Mr" , new Date(10), "3" ,  "New Street" , "London" , "SE12 7LA" , "07890123456" , "bloggsj@gmail.com");
        boolean inserted = customerDAO.insert(newCustomer);
        System.out.println("Insert result: " + inserted);

        // Get all customers
        List<Customer> customers = customerDAO.getAll();
        System.out.println("All customers:");
        for (Customer c : customers) {
            System.out.println(c + "\n");
        }

        // Get customer by primary key (assuming 1 exists)
        Customer foundCustomer = customerDAO.getByPK(1);
        System.out.println("Customer with id=1: " + foundCustomer);

        // Get num of rows
        System.out.println(customerDAO.getNumRows());

        // Update customer
        foundCustomer.setSzEmail("updated.email@example.com");
        boolean updated = customerDAO.update(foundCustomer);
        System.out.println("Update result: " + updated);
        System.out.println(foundCustomer);

        // Delete customer
        // boolean deleted = customerDAO.delete(foundCustomer.getICustomerId());
        // System.out.println("Delete result: " + deleted);

        System.out.println("\n---------------------------------------------------------\n");

        RoomDAO roomDAO = new RoomDAO();

        // Get all rooms
        List<Room> rooms = roomDAO.getAll();
        System.out.println("All Rooms:");
        for (Room r : rooms) {
            System.out.println(r + "\n");
        }

        // Get room by primary key (assuming 1 exists)
        Room foundRoom = roomDAO.getByPK(1);
        System.out.println("Room number 1: " + foundRoom);

        // Get num of rows
        System.out.println(roomDAO.getNumRows());

        System.out.println("\n---------------------------------------------------------\n");

        BookingDAO bookingDAO = new BookingDAO();

        // Insert a booking
        Booking booking = new Booking(1, 1 , new Date(1000000) , 5);
        boolean bookingInserted = bookingDAO.insert(booking);
        System.out.println("Insert result: " + bookingInserted);

        // Get all bookings
        List<Booking> bookings = bookingDAO.getAll();
        System.out.println("All bookings:");
        for (Booking b : bookings) {
            System.out.println(b + "\n");
        }

        // Get booking by primary key (assuming 1 exists)
        Booking foundBooking = bookingDAO.getByPK(1);
        System.out.println("Booking with id=1: " + foundBooking);

        // Get num of rows
        System.out.println(bookingDAO.getNumRows());

        // Update booking
        foundBooking.setINights(2);
        boolean bookingUpdated = bookingDAO.update(foundBooking);
        System.out.println("Update result: " + bookingUpdated);
        System.out.println(foundBooking);

        boolean deleted = bookingDAO.delete(foundBooking.getIBookingId());
        System.out.println("Delete result: " + deleted);

        System.out.println("\n---------------------------------------------------------\n");
    }
}
