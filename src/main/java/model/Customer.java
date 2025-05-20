/**
 * Customer instances store all data provided by the database from Customer table
 * Accessed by CustomerDAO
 * Used when interacting with customer
 * @author Owen Walton
 * Last edit: 21/05/25
 */

package main.java.model;

import java.sql.Date;

public class Customer
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // fields in db-table.
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

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public Customer(int iCustomerId, String szSurname, String szFirstName, Date DOB, String szTitle,
                    String szHouseNumber, String szStreetName, String szCity, String szPostcode,
                    String szPhoneNumber, String szEmail)
    {
        this.iCustomerId = iCustomerId;
        this.szSurname = szSurname;
        this.szFirstName = szFirstName;
        this.DOB = DOB;
        this.szTitle = szTitle;
        this.szHouseNumber = szHouseNumber;
        this.szStreetName = szStreetName;
        this.szCity = szCity;
        this.szPostcode = szPostcode;
        this.szPhoneNumber = szPhoneNumber;
        this.szEmail = szEmail;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
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

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
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
}