-- select the database.
USE Hotel ;

-- Insert all data

INSERT INTO Hotel.Customer ( Surname , FirstName , Title , DOB , HouseNumber , StreetName , City , Postcode , PhoneNumber, Email )
        VALUES ( "Mouse" , "Mickey" , "Mr" , "1970-01-01" , "3" ,  "New Street" , "London" , "SE12 7LA" , "07890123456" , "mousem@gmail.com") ;
INSERT INTO Hotel.Customer ( Surname , FirstName , Title , DOB , HouseNumber , StreetName , City , Postcode , PhoneNumber, Email )
        VALUES ( "Jones" , "Jane" , "Ms" , "1974-02-02" , "10" , "East Road" , "Cornwall" , "EX22 6LB" , "07986543210" , "janej@hotmail.com") ;


INSERT INTO Hotel.Booking ( CustomerId , StartDate , Nights)
      VALUES ( 1 , "2025-05-15" , 5 ) ;


INSERT INTO Hotel.RoomBooking ( RoomNumber , BookingId , Occupants )
      VALUES ( 13 , 1 , 2) ;
INSERT INTO Hotel.RoomBooking ( RoomNumber , BookingId , Occupants )
      VALUES ( 14 , 1 , 2) ;
