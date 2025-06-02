/**
 * Customer instances store all data provided by the database from Customer table
 * Accessed by CustomerDAO
 * Used when interacting with Customer table in DB
 * @author Owen Walton
 */

package main.com.hotel.model.entity;

import java.sql.Date;

public class Customer
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // Fields in db-table.
    private int iCustomerId ;
    private String szSurname ;
    private String szFirstName ;
    private String szTitle ;
    private Date DOB ;
    private String szHouseNumber ;
    private String szStreetName ;
    private String szCity ;
    private String szPostcode ;
    private String szPhoneNumber ;
    private String szEmail ;
    private boolean isActive ;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public Customer()
    {
        clearAttributes();
    }

    public Customer(int iCustomerId, String szSurname, String szFirstName, String szTitle, Date DOB,
                    String szHouseNumber, String szStreetName, String szCity, String szPostcode,
                    String szPhoneNumber, String szEmail, boolean isActive)
    {
        this.iCustomerId = iCustomerId;
        this.szSurname = szSurname;
        this.szFirstName = szFirstName;
        this.szTitle = szTitle;
        this.DOB = DOB;
        this.szHouseNumber = szHouseNumber;
        this.szStreetName = szStreetName;
        this.szCity = szCity;
        this.szPostcode = szPostcode;
        this.szPhoneNumber = szPhoneNumber;
        this.szEmail = szEmail;
        this.isActive = isActive;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    public void clearAttributes()
    {
        this.iCustomerId = -1;
        this.szSurname = null;
        this.szFirstName = null;
        this.szTitle = null;
        this.DOB = null;
        this.szHouseNumber = null;
        this.szStreetName = null;
        this.szCity = null;
        this.szPostcode = null;
        this.szPhoneNumber = null;
        this.szEmail = null;
        this.isActive = false;
    }

    public void setICustomerId(int iCustomerId) {
        this.iCustomerId = iCustomerId;
    }

    public void setSzSurname(String szSurname) {
        this.szSurname = szSurname;
    }

    public void setSzFirstName(String szFirstName) {
        this.szFirstName = szFirstName;
    }

    public void setSzTitle(String szTitle) {
        this.szTitle = szTitle;
    }

    public void setSzHouseNumber(String szHouseNumber) {
        this.szHouseNumber = szHouseNumber;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setSzStreetName(String szStreetName) {
        this.szStreetName = szStreetName;
    }

    public void setSzCity(String szCity) {
        this.szCity = szCity;
    }

    public void setSzPostcode(String szPostcode) {
        this.szPostcode = szPostcode;
    }

    public void setSzPhoneNumber(String szPhoneNumber) {
        this.szPhoneNumber = szPhoneNumber;
    }

    public void setSzEmail(String szEmail) {
        this.szEmail = szEmail;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
    @Override
    public String toString() {
        return "First Name: " + szFirstName + "\n" +
                "Surname: " + szSurname + "\n" +
                "Title: " + szTitle + "\n" +
                "Date of Birth: " + DOB + "\n" +
                "House Number: " + szHouseNumber + "\n" +
                "Street Name: " + szStreetName + "\n" +
                "City: " + szCity + "\n" +
                "Postcode: " + szPostcode + "\n" +
                "Phone Number: " + szPhoneNumber + "\n" +
                "Email: " + szEmail + "\n" +
                "Active: " + isActive;
    }

    public int getICustomerId() {
        return iCustomerId;
    }

    public String getSzSurname() {
        return szSurname;
    }

    public String getSzFirstName() {
        return szFirstName;
    }

    public String getSzTitle() {
        return szTitle;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getSzHouseNumber() {
        return szHouseNumber;
    }

    public String getSzStreetName() {
        return szStreetName;
    }

    public String getSzCity() {
        return szCity;
    }

    public String getSzPostcode() {
        return szPostcode;
    }

    public String getSzPhoneNumber() {
        return szPhoneNumber;
    }

    public String getSzEmail() {
        return szEmail;
    }

    public boolean getIsActive() {
        return isActive;
    }
}