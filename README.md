# Hotel Management System

A console-based Java + SQL application allowing staff to manage hotel operations. The system supports bookings, room cleaning schedules, and breakfast preparation by defining clear role-based access.

---

## Overview

Program functionality is split into three distinct user roles:

- **Concierge** – handle bookings and customer records  
- **Cleaner** – view rooms to clean based on departures  
- **Chef** – manage breakfast counts for upcoming days

**Currently designed for employee access only - not customers.**

A MySQL database stores rooms, customers, and bookings. The application uses layered Java architecture and JDBC to interact with the database.

---

## Core Functionality

- **Concierge** – find available rooms, create bookings & calculate cost, cancel booking, retrieve booking details, add new customers, edit/delete/retrieve customer details  
- **Cleaner** – daily list of rooms to be cleaned  
- **Chef** – breakfast headcount for kitchen planning  

---

## Architecture & Tech

- **Java 16+**, organized with Service, DAO, Access, and UI layers  
- **MySQL** database accessed via **JDBC**  
- **Console interface** – lightweight, no GUI components  
- Focus on **modular design**

---

## Setup & Run

1. Clone this repository
   ```bash
   git clone https://github.com/owen-walton/hotel-management-system
   cd hotel-management-system
   ```
2. Install Java 16 or higher and make sure it's available in your system's PATH

3. Install MySQL 8 or higher with the root password set to `mysqlaccess`.
   If you use a different password, you must manually update the `PASS` variable in `src/main/com/hotel/dbc/DBConnection.java`:  
   ```java
   private static final String PASS = "your_new_password";
   ```

4. Download the MySQL Connector/J from [MySQL's official site](https://dev.mysql.com/downloads/connector/j/), and add the JAR file to your project's classpath.

5. Set up the database:
   ```bash
   mysql -u root -p
   ```
   Then inside the MySQL shell:  
   ```sql
   source SQL/HotelDDL.sql;
   -- OPTIONAL (for test purpose only - not practical use):
   source SQL/InsertTestDataDDL.sql;
   ```

6. Compile and launch the application:  
   ```bash
   javac -cp -d out src/**/*.java
   java -cp MainApp
   ```

---

## Potential Future Implementation

- Create customer detail logs table in database which records every time a staff member pulls customer details for data security
- Use ‘PreparedStatement’ for SQL to increase database safety when accessing
- Implement password authentication for each role accessing the program
- Use dependency injection to allow for the use of Spring
- Make UI web based using HTML, CSS and Javascript
- Add manager role with all permissions
- Add employees table to database with shift patterns so that cleaning schedule can be created with cleaners given specific rooms to clean instead of just showing rooms that require cleaning
- Use merge sort to sort rooms displayed to cleaner by floor and number so cleaners can chronologically view them in the order of cleaning
