/**
 * @author Owen Walton
 * Smart wrapper for service files so head chef doesn't have direct access to service logic
 */
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
