-- select the database.
USE Hotel ;

-- Insert all data

INSERT INTO Hotel.Customer (Surname, FirstName, Title, DOB, HouseNumber, StreetName, City, Postcode, PhoneNumber, Email, IsActive)
VALUES
('Smith', 'John', 'Mr', '1985-07-12', '12A', 'High Street', 'London', 'E1 6AN', '07123456789', 'john.smith@example.com', TRUE),
('Jones', 'Sarah', 'Ms', '1990-03-22', '34', 'Baker Street', 'London', 'NW1 6XE', '07234567891', 'sarah.jones@example.com', TRUE),
('Brown', 'Mike', 'Mr', '1978-11-03', '8', 'Maple Road', 'Manchester', 'M1 1AE', '07345678912', 'mike.brown@example.com', TRUE),
('Taylor', 'Emma', 'Mrs', '1982-06-15', '20B', 'Oak Avenue', 'Leeds', 'LS1 3AB', '07456789123', 'emma.taylor@example.com', TRUE),
('Wilson', 'Jake', 'Mr', '1995-01-28', '5', 'Pine Street', 'Birmingham', 'B2 4QA', '07567891234', 'jake.wilson@example.com', TRUE);


INSERT INTO Hotel.Booking (CustomerId, StartDate, Nights)
VALUES
(1, '2025-06-27', 3),
(2, '2025-07-01', 1),
(3, '2025-07-05', 4),
(4, '2025-07-03', 1),
(5, '2025-07-04', 2),
(2, '2025-08-01', 2);

INSERT INTO Hotel.RoomBooking (RoomNumber, BookingId, Occupants)
VALUES
(1, 1, 2),
(2, 1, 2),
(3, 2, 1),
(4, 2, 2),
(5, 3, 2),
(6, 3, 1),
(7, 3, 1),
(8, 4, 2),
(9, 5, 2),
(10, 5, 1),
(1, 6, 1);
