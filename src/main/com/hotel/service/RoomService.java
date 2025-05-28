package main.com.hotel.service;

import main.com.hotel.dao.RoomDAO;

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
}
