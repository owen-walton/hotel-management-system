/**
 * RoomBooking instances store all data provided by the database from RoomBooking table
 * Accessed by RoomBookingDAO
 * Used when interacting with RoomBooking table in DB
 * @author Owen Walton
 */

package main.com.hotel.model.entity;

public class RoomBooking
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // Fields in db-table.
    private int iRoomBookingID;
    private int iRoomNumber;
    private int iBookingId;
    private short iOccupants;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public RoomBooking(int iRoomBookingID, int iRoomNumber, int iBookingId, short iOccupants)
    {
        this.iRoomBookingID = iRoomBookingID;
        this.iRoomNumber = iRoomNumber;
        this.iBookingId = iBookingId;
        this.iOccupants = iOccupants;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    public void setIRoomBookingID(int iRoomBookingID) {
        this.iRoomBookingID = iRoomBookingID;
    }

    public void setIRoomNumber(int iRoomNumber) {
        this.iRoomNumber = iRoomNumber;
    }

    public void setIBookingId(int iBookingId) {
        this.iBookingId = iBookingId;
    }

    public void setIOccupants(short iOccupants) {
        this.iOccupants = iOccupants;
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
    public int getIRoomBookingID() {
        return iRoomBookingID;
    }

    public int getIRoomNumber() {
        return iRoomNumber;
    }

    public int getIBookingId() {
        return iBookingId;
    }

    public short getIOccupants() {
        return iOccupants;
    }
}