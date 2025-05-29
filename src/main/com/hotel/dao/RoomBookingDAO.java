/**
 *
 * This is a wrapper file used to handle interaction with RoomBooking table in the database
 * @author Owen Walton
 *
 **/

package main.com.hotel.dao;

import main.com.hotel.dbc.DBConnection;
import main.com.hotel.model.entity.RoomBooking;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class RoomBookingDAO implements ReadOnlyDAO<RoomBooking>, WriteOnlyDAO<RoomBooking>{
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
    public RoomBookingDAO()
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
    public RoomBooking getByPK(int pk)
    {
        RoomBooking roomBooking;

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT RoomBookingId"
                    + ", RoomNumber"
                    + ", BookingId"
                    + ", Occupants"
                    + " FROM RoomBooking"
                    + " WHERE RoomBookingId = " + pk
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in room booking variable
            roomBooking = new RoomBooking(
                    resultSet.getInt("RoomBookingId"),
                    resultSet.getInt("RoomNumber"),
                    resultSet.getInt("BookingId"),
                    resultSet.getShort("Occupants")
            );

            return roomBooking;
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

    public List<RoomBooking> getByBookingId(int bookingId)
    {
        List<RoomBooking> roomBookings = new ArrayList<>();

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT RoomBookingId"
                    + ", RoomNumber"
                    + ", BookingId"
                    + ", Occupants"
                    + " FROM RoomBooking"
                    + " WHERE BookingId = " + bookingId
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in room booking variable
            while(resultSet.next())
            {
                roomBookings.add(new RoomBooking(
                        resultSet.getInt("RoomBookingId"),
                        resultSet.getInt("RoomNumber"),
                        resultSet.getInt("BookingId"),
                        resultSet.getShort("Occupants")
                ));
            }


            return roomBookings;
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
            sql = "SELECT COUNT(*) AS total FROM RoomBooking;";

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
    public List<RoomBooking> getAll()
    {
        List<RoomBooking> roomBookingList = new ArrayList<>();

        for (int i = 1; i <= getNumRows(); i++)
        {
            roomBookingList.add(getByPK(i));
        }
        return roomBookingList;
    }

    public int getOccupantsByBookingId(int bookingId)
    {
        int occupants;

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT SUM(Occupants) AS total_occupants "
                    + "FROM RoomBooking "
                    + "WHERE BookingId = " + bookingId
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            if (resultSet.next())
            {
                // store result of query in room booking variable
                occupants = resultSet.getInt("total_occupants");
                if (resultSet.wasNull())
                {
                    occupants = 0;
                }
            }
            else
            {
                occupants = 0;
            }

            return occupants;
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
    // ----------------------------------------------------------------------
    // Implementation of WriteOnlyDAO
    // ----------------------------------------------------------------------
    @Override
    public boolean insert(RoomBooking roomBooking)
    {
        // bRC stores whether insert has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "INSERT INTO RoomBooking( "
                    + "RoomBookingId"
                    + ", RoomNumber"
                    + ", BookingId"
                    + ", Occupants"
                    + " ) "
                    + " VALUES( "
                    + "\"" + roomBooking.getIRoomBookingID()  + "\""
                    + ", \"" + roomBooking.getIRoomNumber() + "\""
                    + ", \"" + roomBooking.getIBookingId() + "\""
                    + ", \"" + roomBooking.getIOccupants() + "\""
                    + " ) ; " ;

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

    @Override
    public boolean update(RoomBooking roomBooking)
    {
        // bRC stores whether update has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "UPDATE RoomBooking SET ";

            int count = 0; // can break out of function if all of room booking's fields are null (nothing to update)

            // add all updates that aren't null
            if (roomBooking.getIRoomNumber() != 0)
            {
                sql = sql + "RoomNumber = " + roomBooking.getIRoomNumber() + ", ";
                count++;
            }
            if (roomBooking.getIBookingId() != 0)
            {
                sql = sql + "BookingId = " + roomBooking.getIBookingId() + ", ";
                count++;
            }
            if (roomBooking.getIOccupants() != 0)
            {
                sql = sql + "Occupants = " + roomBooking.getIOccupants() + ", ";
                count++;
            }

            if (count == 0)
            {
                return false;
            }

            sql = sql + " WHERE RoomBookingId = " + roomBooking.getIRoomBookingID() + ";";
            // remove comma from last value
            if (sql.matches(", WHERE"))
            {
                sql = sql.replaceAll(", WHERE", " WHERE");
            }

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

    @Override
    public boolean delete(int pk)
    {
        // bRC stores whether delete has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "DELETE FROM RoomBooking WHERE RoomBookingId = " + pk + ";";

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

    public boolean deleteByBookingId(int bookingId)
    {
        boolean bRC = false;

        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "DELETE FROM RoomBooking WHERE BookingId = " + bookingId + ";";

            sqlStatement = dbConnection.getConnection().createStatement() ;
            iRC = sqlStatement.executeUpdate( sql ) ;

            // iRC will hold how many records were updated or inserted.  zero is bad in this case.
            if (iRC > 0)
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
