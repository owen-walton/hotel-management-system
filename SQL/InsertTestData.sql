-- select the database.
USE Hotel ;

-- Insert all data

INSERT INTO Hotel.Customer ( Surname , FirstName , Title , DOB , HouseNumber , StreetName , City , Postcode , PhoneNumber, Email )
        VALUES ( "Mouse" , "Mickey" , "Mr" , "1970-01-01" , "3" ,  "New Street" , "London" , "SE12 7LA" , "07890123456" , "mousem@gmail.com") ;
INSERT INTO Hotel.Customer ( Surname , FirstName , Title , DOB , HouseNumber , StreetName , City , Postcode , PhoneNumber, Email )
        VALUES ( "Jones" , "Jane" , "Ms" , "1974-02-02" , "10" , "East Road" , "Cornwall" , "EX22 6LB" , "07986543210" , "janej@hotmail.com") ;


INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (1, 2, 'Twin', TRUE, FALSE, TRUE, FALSE, FALSE, 1, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (2, 3, 'Triple', TRUE, FALSE, TRUE, TRUE, FALSE, 1, 120);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (3, 2, 'Double', TRUE, TRUE, TRUE, TRUE, TRUE, 1, 100);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (4, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 1, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (5, 2, 'Queen', TRUE, TRUE, TRUE, FALSE, TRUE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (6, 1, 'Single', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 70);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (7, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (8, 2, 'Twin', TRUE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (9, 2, 'Double/Twin', FALSE, FALSE, FALSE, FALSE, FALSE, 2, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (10, 2, 'Double', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (11, 1, 'Single', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 80);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (12, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 3, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (13, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (14, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (15, 2, 'Twin', FALSE, FALSE, FALSE, FALSE, FALSE, 3, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (16, 3, 'Double/Single', FALSE, FALSE, FALSE, FALSE, FALSE, 4, 100);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (17, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 4, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (18, 2, 'Double', TRUE, FALSE, FALSE, FALSE, FALSE, 4, 110);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (19, 2, 'Twin', TRUE, FALSE, FALSE, FALSE, FALSE, 4, 90);

INSERT INTO Hotel.Room (
    RoomNumber, Occupancy, RoomType, HasShower, HasJacuzzi, HasSeaView,
    IsFamilyRoom, IsHoneymoonRoom, Floor, £perNight
) VALUES (20, 2, 'Double', TRUE, TRUE, TRUE, FALSE, TRUE, 4, 110);


INSERT INTO Hotel.Booking ( CustomerId , StartDate , Nights)
      VALUES ( 1 , "2025-05-15" , 5 ) ;

INSERT INTO Hotel.RoomBooking ( RoomNumber , BookingId , Occupants )
      VALUES ( 13 , 1 , 2) ;
INSERT INTO Hotel.RoomBooking ( RoomNumber , BookingId , Occupants )
      VALUES ( 14 , 1 , 2) ;
