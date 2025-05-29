package main.com.hotel.service;

import main.com.hotel.dao.BookingDAO;
import main.com.hotel.dao.RoomBookingDAO;
import main.com.hotel.model.entity.Booking;
import main.com.hotel.model.entity.BookingResult;
import main.com.hotel.model.entity.RoomBooking;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingService {

    private final BookingDAO bookingDAO;
    private final RoomBookingDAO roomBookingDAO;

    public BookingService()
    {
        this.bookingDAO = new BookingDAO();
        this.roomBookingDAO = new RoomBookingDAO();
    }

    // gets num of guests that stayed in the hotel the night before each date, from the current date to iDays days later
    public Object[][] getNumOfGuests(int iDays)
    {
        Object[][] numOfGuests = new Object[2][iDays];
        final LocalDate CURRENT_DATE = LocalDate.now();

        for (int i = 0; i < iDays; i++)
        {
            Date dayAtIndexI = Date.valueOf(CURRENT_DATE.plusDays(i));

            numOfGuests[0][i] = dayAtIndexI;
            numOfGuests[1][i] = getNumOfGuestsLastNightByDate(dayAtIndexI);
        }

        return numOfGuests;
    }

    // number of guests that slept in the hotel night before the date passed in
    // (also the number of guests eating breakfast)
    public int getNumOfGuestsLastNightByDate(Date date)
    {
        List<Integer> activeBookingIds = getBookingIdsLastNightByDate(date);
        int total = 0;

        for (Integer bookingId : activeBookingIds)
        {
            total = total + roomBookingDAO.getOccupantsByBookingId(bookingId);

        }
        return total;
    }

    public List<Integer> getBookingIdsLastNightByDate(Date date)
    {
        List<Integer> activeBookingIds = new ArrayList<>();
        List<Booking> allBookings = bookingDAO.getAll();

        for (Booking booking : allBookings) {
            final LocalDate START_DATE = booking.getStartDate().toLocalDate();

            for (int i = 1; i <= booking.getINights(); i++)
            {
                if (START_DATE.plusDays(i).equals(date.toLocalDate()))
                {
                    activeBookingIds.add(booking.getIBookingId());
                }
            }
        }

        return activeBookingIds;
    }

    public List<Integer> getRoomNumbersEndingByDate(Date date)
    {
        List<Integer> endingRoomNumbers = new ArrayList<>();
        List<Booking> allBookings = bookingDAO.getAll();

        for (Booking booking : allBookings)
        {
            final LocalDate END_DATE = booking.getStartDate().toLocalDate().plusDays(booking.getINights());

                if (END_DATE.equals(date.toLocalDate()))
                {
                    List<RoomBooking> roomBookingsEnding = roomBookingDAO.getByBookingId(booking.getIBookingId());
                    for (RoomBooking endingRoomBooking : roomBookingsEnding)
                    {
                        endingRoomNumbers.add(endingRoomBooking.getIRoomNumber());
                    }
                }
        }

        return endingRoomNumbers;
    }

    public BookingResult addBooking(int customerId, Date startDate, int iNights, Map<Integer, Integer> selectedRooms)
    {
        // future change, should only edit 1 table if both are correctly edited
        Booking booking = new Booking(-1, customerId, startDate, iNights);
        List<RoomBooking> roomBookings = new ArrayList<>(); // Map<RoomNumber, Occupants>

        // add to Booking table
        int bookingId = bookingDAO.insert(booking);
        booking.setIBookingId(bookingId);

        // add to RoomBooking table
        for (Map.Entry<Integer, Integer> roomBookingMap : selectedRooms.entrySet())
        {
            RoomBooking roomBooking = new RoomBooking(-1, roomBookingMap.getKey(), bookingId, roomBookingMap.getValue().shortValue());
            int roomBookingId = roomBookingDAO.insert(roomBooking);
            roomBooking.setIRoomBookingID(roomBookingId);
            roomBookings.add(roomBooking);
        }

        return new BookingResult(booking, roomBookings);
    }
}
