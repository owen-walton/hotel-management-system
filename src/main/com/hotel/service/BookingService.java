package main.com.hotel.service;

import main.com.hotel.dao.BookingDAO;
import main.com.hotel.dao.RoomBookingDAO;
import main.com.hotel.model.Booking;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
