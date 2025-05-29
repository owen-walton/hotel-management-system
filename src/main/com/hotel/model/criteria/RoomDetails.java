package main.com.hotel.model.criteria;

public record RoomDetails(
        String roomType,
        Boolean hasShower,
        Boolean hasJacuzzi,
        Boolean hasSeaView,
        Boolean isFamilyRoom,
        Boolean isHoneymoonRoom
) {}