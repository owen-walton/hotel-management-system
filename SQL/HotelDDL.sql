-- Using MySQL

DROP DATABASE IF EXISTS Hotel ;
CREATE DATABASE Hotel ;

-- select the database.
USE Hotel ;

-- Create Customer table
CREATE TABLE Hotel.Customer
(
        CustomerId      INT     NOT NULL        AUTO_INCREMENT ,
        Surname         CHAR( 30 )      NOT NULL ,
        FirstName       CHAR( 30 )      NOT NULL ,
        Title           CHAR( 10 )      NOT NULL ,
        DOB             DATE      NOT NULL ,
        HouseNumber     CHAR( 30 )      NOT NULL , -- use char instead of int because some houses have names/letters
        StreetName      CHAR( 100 )      NOT NULL ,
        City            CHAR( 30 )      NOT NULL ,
        Postcode        CHAR( 12 )      NOT NULL ,
        PhoneNumber     CHAR( 11 )      NOT NULL ,
        Email           CHAR( 40 )      NOT NULL ,
        PRIMARY KEY ( CustomerId )
) ;

-- Create Booking table
CREATE TABLE Hotel.Booking
(
        BookingId      INT     NOT NULL        AUTO_INCREMENT ,
        CustomerId     INT      NOT NULL ,
        StartDate      DATE     NOT NULL ,
        Nights         INT     NOT NULL ,
        PRIMARY KEY ( BookingId ) ,
        FOREIGN KEY ( CustomerId ) REFERENCES Hotel.Customer( CustomerId )
) ;

-- Create Room table
CREATE TABLE Hotel.Room
(
        RoomNumber      INT     NOT NULL ,
        Occupancy       SMALLINT     NOT NULL ,
        RoomType        CHAR(15)     NOT NULL ,
        HasShower       Boolean     NOT NULL ,
        HasJacuzzi      Boolean     NOT NULL ,
        HasSeaView      Boolean     NOT NULL ,
        IsFamilyRoom    Boolean     NOT NULL ,
        IsHoneymoonRoom    Boolean     NOT NULL ,
        Floor           SMALLINT     NOT NULL ,
        £perNight       INT     NOT NULL ,
        PRIMARY KEY ( RoomNumber )
) ;

-- Create assign/join table for Room and Booking
CREATE TABLE Hotel.RoomBooking
(
        RoomBookingID      INT     NOT NULL        AUTO_INCREMENT ,
        RoomNumber         INT     NOT NULL ,
        BookingId          INT     NOT NULL ,
        Occupants          SMALLINT     NOT NULL ,
        PRIMARY KEY ( RoomBookingID ) ,
        FOREIGN KEY ( RoomNumber) REFERENCES Hotel.Room( RoomNumber ) ,
        FOREIGN KEY ( BookingId ) REFERENCES Hotel.Booking( BookingId )
) ;
