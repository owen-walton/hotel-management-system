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
        PoundsPerNight       INT     NOT NULL ,
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

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (1, 2, 'Twin', TRUE, FALSE, TRUE, FALSE, FALSE, 1, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (2, 3, 'Triple', TRUE, FALSE, TRUE, TRUE, FALSE, 1, 120);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (3, 2, 'Double', TRUE, TRUE, TRUE, TRUE, TRUE, 1, 100);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (4, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 1, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (5, 2, 'Queen', TRUE, TRUE, TRUE, FALSE, TRUE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (6, 1, 'Single', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 70);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (7, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (8, 2, 'Twin', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (9, 2, 'Double/Twin', FALSE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (10, 2, 'Double', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (11, 1, 'Single', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 80);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (12, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 3, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (13, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (14, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (15, 2, 'Twin', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (16, 3, 'Double/Single', FALSE, FALSE, FALSE, FALSE, FALSE, 4, 100);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (17, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 4, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (18, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 4, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (19, 2, 'Twin', TRUE, FALSE, FALSE, FALSE, FALSE, 4, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, PoundsPerNight
) VALUES (20, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 4, 110);
