package main.com.hotel.role.access;

import main.com.hotel.service.BookingService;
import main.com.hotel.service.RoomService;

import java.sql.Date;
import java.util.Map;

public class HeadCleanerAccess
{
    private final BookingService bookingService;
    private final RoomService roomService;

    public HeadCleanerAccess()
    {
        this.bookingService = new BookingService();
        this.roomService = new RoomService();
    }

    public Map<Integer, Integer> getTodaysRooms()
    {
        return roomService.addFloorToRoomList(
                bookingService.getRoomNumbersEndingByDate(new Date(System.currentTimeMillis()))
                );
    }

}