package main.com.hotel.service;

import main.com.hotel.dao.RoomDAO;
import main.com.hotel.model.criteria.RoomDetails;
import main.com.hotel.model.entity.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomService
{
    private RoomDAO roomDAO;

    public RoomService()
    {
        this.roomDAO = new RoomDAO();
    }

    public Map<Integer, Integer> addFloorToRoomList(List<Integer> roomNumbers)
    {
        Map<Integer, Integer> roomAndFloor = new HashMap<>();

        for(Integer num: roomNumbers)
        {
            roomAndFloor.put(num, (int) roomDAO.getByPK(num).getIFloor());
        }

        return roomAndFloor;
    }

    public Room getCheapestRoom(List<Room> rooms)
    {
        Room cheapest = rooms.get(0);
        for (Room room : rooms) {
            if (room.getIPoundsPerNight() < cheapest.getIPoundsPerNight()) {
                cheapest = room;
            }
        }
        return cheapest;
    }

    public List<Room> getMatchingRooms(int occupants, RoomDetails details, List<Integer> excludedRoomNumbers)
    {
        List<Room> rooms = roomDAO.getMatchingRooms(occupants, details);

        if (rooms != null)
        {
            for (int num : excludedRoomNumbers)
            {
                rooms.removeIf(room -> room.getIRoomNumber() == num);
            }
        }

        return rooms;
    }

    public int getRoomCost(int roomNumber)
    {
        return roomDAO.getByPK(roomNumber).getIPoundsPerNight();
    }
}
