package main.com.hotel.model.entity;

import main.com.hotel.dao.CustomerDAO;
import main.com.hotel.dao.RoomDAO;

import java.util.List;

public record BookingResult(Booking booking, List<RoomBooking> roomBookings) {
    @Override
    public String toString()
    {
        final CustomerDAO customerDAO = new CustomerDAO();
        final RoomDAO roomDAO = new RoomDAO();
        String rooms = "";

        for (int i = 0; i < roomBookings.size(); i++)
        {
            rooms = rooms + roomDAO.getByPK(roomBookings.get(i).getIRoomNumber()) + "\n";
        }

        // Return full details of the booking with total cost at the end
        return "Surname: " + customerDAO.getByPK(booking.getICustomerId()).getSzSurname()
                + "\nNumber of rooms: " + roomBookings.size()
                + "\nStart date: " + booking.getStartDate()
                + "\nNights: " + booking.getINights()
                + "\nRooms:\n"
                + rooms;
    }
}
