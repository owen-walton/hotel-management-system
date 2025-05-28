/**
 * @author Owen Walton
 */
package main.com.hotel.ui;

import main.com.hotel.role.access.HeadCleanerAccess;

import java.util.List;
import java.util.Map;

public class HeadCleanerUI implements BaseUI
{
    HeadCleanerAccess access;

    public HeadCleanerUI()
    {
        this.access = new HeadCleanerAccess();
    }

    @Override
    public void run()
    {
        Map<Integer, Integer> todaysRooms = access.getTodaysRooms();
        displayTodaysRooms(todaysRooms);
    }

    public void displayTodaysRooms(Map<Integer, Integer> todaysRooms)
    {
        if (todaysRooms == null || todaysRooms.isEmpty())
        {
            System.out.println("No rooms require cleaning today.");
            return;
        }
        System.out.println("The following rooms require cleaning today:");
        System.out.printf("%-12s | %-5s%n", "Room Number", "Floor");
        System.out.println("-------------+-------");

        for (Map.Entry<Integer, Integer> room : todaysRooms.entrySet())
        {
            System.out.printf("%-12d | %-5d%n", room.getKey(), room.getValue());
        }
    }
}
