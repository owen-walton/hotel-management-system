/**
 * Room instances store all data provided by the database from Room table
 * Accessed by RoomDAO
 * Used when interacting with Room table in DB
 * @author Owen Walton
 * Last edit: 21/05/25
 */

package main.java.model;

public class Room
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // Fields in db-table.
    private int iRoomNumber;
    private short iOccupancy;
    private String szRoomType;
    private boolean bHasShower;
    private boolean bHasJacuzzi;
    private boolean bHasSeaView;
    private boolean bIsFamilyRoom;
    private boolean bIsHoneymoonRoom;
    private short iFloor;
    private int iPricePerNight;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public Room(int iRoomNumber, short iOccupancy, String szRoomType, boolean bHasShower, boolean bHasJacuzzi,
                boolean bHasSeaView, boolean bIsFamilyRoom, boolean bIsHoneymoonRoom, short iFloor, int iPricePerNight)
    {
        this.iRoomNumber = iRoomNumber;
        this.iOccupancy = iOccupancy;
        this.szRoomType = szRoomType;
        this.bHasShower = bHasShower;
        this.bHasJacuzzi = bHasJacuzzi;
        this.bHasSeaView = bHasSeaView;
        this.bIsFamilyRoom = bIsFamilyRoom;
        this.bIsHoneymoonRoom = bIsHoneymoonRoom;
        this.iFloor = iFloor;
        this.iPricePerNight = iPricePerNight;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    public void setIRoomNumber(int iRoomNumber) {
        this.iRoomNumber = iRoomNumber;
    }

    public void setIOccupancy(short iOccupancy) {
        this.iOccupancy = iOccupancy;
    }

    public void setSzRoomType(String szRoomType) {
        this.szRoomType = szRoomType;
    }

    public void setBHasShower(boolean bHasShower) {
        this.bHasShower = bHasShower;
    }

    public void setBHasJacuzzi(boolean bHasJacuzzi) {
        this.bHasJacuzzi = bHasJacuzzi;
    }

    public void setBHasSeaView(boolean bHasSeaView) {
        this.bHasSeaView = bHasSeaView;
    }

    public void setBIsFamilyRoom(boolean bIsFamilyRoom) {
        this.bIsFamilyRoom = bIsFamilyRoom;
    }

    public void setBIsHoneymoonRoom(boolean bIsHoneymoonRoom) {
        this.bIsHoneymoonRoom = bIsHoneymoonRoom;
    }

    public void setIFloor(short iFloor) {
        this.iFloor = iFloor;
    }

    public void setIPricePerNight(int iPricePerNight) {
        this.iPricePerNight = iPricePerNight;
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
    public int getIRoomNumber() {
        return iRoomNumber;
    }

    public short getIOccupancy() {
        return iOccupancy;
    }

    public String getSzRoomType() {
        return szRoomType;
    }

    public boolean getBHasShower() {
        return bHasShower;
    }

    public boolean getBHasJacuzzi() {
        return bHasJacuzzi;
    }

    public boolean getBHasSeaView() {
        return bHasSeaView;
    }

    public boolean getBIsFamilyRoom() {
        return bIsFamilyRoom;
    }

    public boolean getBIsHoneymoonRoom() {
        return bIsHoneymoonRoom;
    }

    public short getIFloor() {
        return iFloor;
    }

    public int getIPricePerNight() {
        return iPricePerNight;
    }
}