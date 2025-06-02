/**
 * @author Owen Walton
 */
package main.com.hotel.ui;

import main.com.hotel.role.access.HeadChefAccess;

public class HeadChefUI implements BaseUI
{
    private final HeadChefAccess access;

    public HeadChefUI()
    {
        this.access = new HeadChefAccess();
    }

    @Override
    public void run()
    {
        int iDays;

        iDays = InputHelper.inputInteger(1, 21, "How many days do you want to retrieve the guest count for? (Max 21): ");
        displayNumberOfGuestsForBreakfast(iDays);
    }

    public void displayNumberOfGuestsForBreakfast(int iDays)
    {
        Object[][] numOfGuests = access.getNumOfGuestsForBreakfast(iDays);

        System.out.printf("%-15s | %-25s%n", "Date", "Guests that require breakfast");
        System.out.println("---------------------------------------------");

        for (int i = 0; i < numOfGuests[0].length; i++)
        {
            System.out.printf("%-15s | %-25s%n", numOfGuests[0][i], numOfGuests[1][i]);
        }

        System.out.println("\nPlease be aware that this may change if extra bookings are made.");
    }
}
