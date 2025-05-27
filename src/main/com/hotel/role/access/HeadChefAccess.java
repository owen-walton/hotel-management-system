package main.com.hotel.role.access;

import main.com.hotel.service.BookingService;

public class HeadChefAccess
{
    private final BookingService bookingService;

    public HeadChefAccess()
    {
        this.bookingService = new BookingService();
    }

    public Object[][] getNumOfGuestsForBreakfast(int iDays)
    {
        return bookingService.getNumOfGuests(iDays);
    }
}
