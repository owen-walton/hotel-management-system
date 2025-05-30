/**
 *
 * This is a wrapper file used to handle interaction with Booking table in the database
 * @author Owen Walton
 *
 **/

package main.com.hotel.dao;

import main.com.hotel.dbc.DBConnection;
import main.com.hotel.model.entity.Booking;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements ReadOnlyDAO<Booking>, WriteOnlyDAO<Booking>{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // DB materials.
    private String sql;

    private Statement sqlStatement = null;
    private ResultSet resultSet = null;

    private DBConnection dbConnection;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public BookingDAO()
    {
        reset() ;
        this.dbConnection = new DBConnection() ;
        dbConnection.connect();
        if (!dbConnection.isConnected()) {
            System.err.println(this.getClass().getName() + "Error: Failed to connect to DB");
        }
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    protected void reset()
    {
        sql = null;
        sqlStatement = null;
        resultSet = null;
    }

    // ----------------------------------------------------------------------
    // Implementation of ReadOnlyDAO
    // ----------------------------------------------------------------------
    @Override
    public Booking getByPK(int pk)
    {
        Booking booking;

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT BookingId"
                    + ", CustomerId"
                    + ", StartDate"
                    + ", Nights"
                    + " FROM Booking"
                    + " WHERE BookingId = " + pk
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in booking variable
            booking = new Booking(
                    resultSet.getInt("BookingId"),
                    resultSet.getInt("CustomerId"),
                    resultSet.getDate("StartDate"),
                    resultSet.getInt("Nights")
            );

            return booking;
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ": SQL error: " + se ) ;
            se.printStackTrace();

            return null;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ": Error: " + e ) ;
            e.printStackTrace() ;

            return null;
        }

    }

    @Override
    public int getNumRows()
    {
        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT COUNT(*) AS total FROM Booking;";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in count variable
            return resultSet.getInt("total");
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ": SQL error: " + se ) ;
            se.printStackTrace();

            return -1;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ": Error: " + e ) ;
            e.printStackTrace() ;

            return -1;
        }
    }

    @Override
    public List<Booking> getAll()
    {
        List<Booking> bookingList = new ArrayList<>();

        for (int i = 1; i <= getNumRows(); i++)
        {
            bookingList.add(getByPK(i));
        }
        return bookingList;
    }

    public List<Booking> getByCustomerId(int customerId)
    {
        List<Booking> bookings = new ArrayList<>();

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT BookingId"
                    + ", CustomerId"
                    + ", StartDate"
                    + ", Nights"
                    + " FROM Booking"
                    + " WHERE CustomerId = " + customerId
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in bookings variable
            while(resultSet.next())
            {
                bookings.add(new Booking(
                        resultSet.getInt("BookingId"),
                        resultSet.getInt("CustomerId"),
                        resultSet.getDate("StartDate"),
                        resultSet.getInt("Nights")));
            }

            return bookings;
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ": SQL error: " + se ) ;
            se.printStackTrace();

            return null;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ": Error: " + e ) ;
            e.printStackTrace() ;

            return null;
        }
    }

    // ----------------------------------------------------------------------
    // Implementation of WriteOnlyDAO
    // ----------------------------------------------------------------------
    @Override
    public int insert(Booking booking)
    {
        // iRC stores whether insert has worked
        int iRC;
        int newId = -1;

        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "INSERT INTO Booking( "
                + "CustomerId"
                + ", StartDate"
                + ", Nights"
                    + " ) "
                    + " VALUES( " + booking.getICustomerId()
                    + ", \"" + booking.getStartDate() + "\""
                    + ", \"" + booking.getINights() + "\""
                    + " ) ; " ;

            sqlStatement = dbConnection.getConnection().createStatement() ;
            iRC = sqlStatement.executeUpdate( sql , Statement.RETURN_GENERATED_KEYS) ;

            // iRC will hold how many records were updated or inserted.  zero is bad in this case.
            if ( iRC == 1 )
            {
                ResultSet rs = sqlStatement.getGeneratedKeys();
                if(rs.next())
                {
                    newId = rs.getInt(1);
                }
            }
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ":: SQL error:: " + se ) ;
            se.printStackTrace() ;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ":: Error:: " + e ) ;
            e.printStackTrace() ;
        }

        return newId;
    }

    @Override
    public boolean update(Booking booking)
    {
        // bRC stores whether update has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "UPDATE Booking SET ";

            int count = 0; // can break out of function if all of booking's fields are null (nothing to update)

            // add all updates that aren't null
            if (booking.getICustomerId() != 0)
            {
                sql = sql + "CustomerId = " + booking.getICustomerId() + ", ";
                count++;
            }
            if (booking.getStartDate() != null)
            {
                sql = sql + "StartDate = '" + booking.getStartDate() + "', ";
                count++;
            }
            if (booking.getINights() != 0)
            {
                sql = sql + "Nights = " + booking.getINights() + ", ";
                count++;
            }

            if (count == 0)
            {
                return false;
            }

            sql = sql.substring(0, sql.length() - 2);
            sql = sql + " WHERE BookingId = " + booking.getIBookingId() + ";";

            sqlStatement = dbConnection.getConnection().createStatement() ;
            iRC = sqlStatement.executeUpdate( sql ) ;

            // iRC will hold how many records were updated or inserted.  zero is bad in this case.
            if ( iRC != 0 )
            {
                bRC = true;
            }
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ":: SQL error:: " + se ) ;
            se.printStackTrace() ;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ":: Error:: " + e ) ;
            e.printStackTrace() ;
        }

        return bRC;
    }

    // any occurrence of pk (bookingId) in RoomBooking must be deleted first (handled in service layer)
    @Override
    public boolean delete(int pk)
    {
        // bRC stores whether delete has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "DELETE FROM Booking WHERE BookingId = " + pk + ";";

            sqlStatement = dbConnection.getConnection().createStatement() ;
            iRC = sqlStatement.executeUpdate( sql ) ;

            // iRC will hold how many records were updated or inserted.  zero is bad in this case.
            if ( iRC == 1 )
            {
                bRC = true;
            }
        }
        catch( SQLException se )
        {
            System.err.println( this.getClass().getName() + ":: SQL error:: " + se ) ;
            se.printStackTrace() ;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + ":: Error:: " + e ) ;
            e.printStackTrace() ;
        }

        return bRC;
    }
}
