/**
 * Booking instances store all data provided by the database from Booking table
 * Accessed by BookingDAO
 * Used when interacting with Booking table in DB
 * @author Owen Walton
 * Last edit: 21/05/25
 */

package main.java.model;

import java.sql.Date;

public class Booking
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // Fields in db-table.
    private int iBookingId;
    private int iCustomerId;
    private Date startDate;
    private int iNights;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public Booking(int iBookingId, int iCustomerId, Date startDate, int iNights)
    {
        this.iBookingId = iBookingId;
        this.iCustomerId = iCustomerId;
        this.startDate = startDate;
        this.iNights = iNights;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    public void setIBookingId(int iBookingId) {
        this.iBookingId = iBookingId;
    }

    public void setICustomerId(int iCustomerId) {
        this.iCustomerId = iCustomerId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setINights(int iNights) {
        this.iNights = iNights;
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
    public int getIBookingId() {
        return iBookingId;
    }

    public int getICustomerId() {
        return iCustomerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getINights() {
        return iNights;
    }
}