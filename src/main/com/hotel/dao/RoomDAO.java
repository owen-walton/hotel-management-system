/**
 *
 * This is a wrapper file used to handle interaction with Room table in the database
 * @author Owen Walton
 *
 **/

package main.com.hotel.dao;

import main.com.hotel.model.criteria.RoomDetails;
import main.com.hotel.model.entity.Room;
import main.com.hotel.dbc.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements ReadOnlyDAO<Room>{
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
    public RoomDAO()
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
    public Room getByPK(int pk)
    {
        Room room;

        try {
            

            // create query
            sql = "SELECT RoomNumber"
                    + ", Occupancy"
                    + ", RoomType"
                    + ", HasShower"
                    + ", HasJacuzzi"
                    + ", HasSeaView"
                    + ", IsFamilyRoom"
                    + ", IsHoneymoonRoom"
                    + ", Floor"
                    + ", PoundsPerNight"
                    + " FROM Room"
                    + " WHERE RoomNumber = " + pk
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in room variable
            room = new Room(resultSet.getInt("RoomNumber"),
                    resultSet.getShort("Occupancy"),
                    resultSet.getString("RoomType"),
                    resultSet.getBoolean("HasShower"),
                    resultSet.getBoolean("HasJacuzzi"),
                    resultSet.getBoolean("HasSeaView"),
                    resultSet.getBoolean( "IsFamilyRoom" ),
                    resultSet.getBoolean( "IsHoneymoonRoom" ),
                    resultSet.getShort( "Floor" ),
                    resultSet.getInt( "PoundsPerNight" ));

            return room;
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
            

            // create query
            sql = "SELECT COUNT(*) AS total FROM Room;";

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
    public List<Room> getAll()
    {
        List<Room> roomList = new ArrayList<>();

        try {
            

            // create query
            sql = "SELECT RoomNumber"
                    + ", Occupancy"
                    + ", RoomType"
                    + ", HasShower"
                    + ", HasJacuzzi"
                    + ", HasSeaView"
                    + ", IsFamilyRoom"
                    + ", IsHoneymoonRoom"
                    + ", Floor"
                    + ", PoundsPerNight"
                    + " FROM Room"
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in room variable
            while(resultSet.next())
            {
                roomList.add(new Room(resultSet.getInt("RoomNumber"),
                        resultSet.getShort("Occupancy"),
                        resultSet.getString("RoomType"),
                        resultSet.getBoolean("HasShower"),
                        resultSet.getBoolean("HasJacuzzi"),
                        resultSet.getBoolean("HasSeaView"),
                        resultSet.getBoolean( "IsFamilyRoom" ),
                        resultSet.getBoolean( "IsHoneymoonRoom" ),
                        resultSet.getShort( "Floor" ),
                        resultSet.getInt( "PoundsPerNight" )));

            }

            return roomList;
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

    public List<Room> getMatchingRooms(int occupants, RoomDetails details)
    {
        List<Room> roomList = new ArrayList<>();

        try {
            

            // create query
            sql = "SELECT RoomNumber"
                    + ", Occupancy"
                    + ", RoomType"
                    + ", HasShower"
                    + ", HasJacuzzi"
                    + ", HasSeaView"
                    + ", IsFamilyRoom"
                    + ", IsHoneymoonRoom"
                    + ", Floor"
                    + ", PoundsPerNight"
                    + " FROM Room"
                    + " WHERE";

            sql += " Occupancy >= " + occupants;

            if (details.roomType() != null)
            {
                sql += " AND RoomType = '" + details.roomType() + "'";
            }
            if (details.hasShower() != null)
            {
                sql += " AND HasShower = " + details.hasShower();
            }
            if (details.hasJacuzzi() != null)
            {
                sql += " AND HasJacuzzi = " + details.hasJacuzzi();
            }
            if (details.hasSeaView() != null)
            {
                sql += " AND HasSeaView = " + details.hasSeaView();
            }
            if (details.isFamilyRoom() != null)
            {
                sql += " AND IsFamilyRoom = " + details.isFamilyRoom();
            }
            if (details.isHoneymoonRoom() != null)
            {
                sql += " AND IsHoneymoonRoom = " + details.isHoneymoonRoom();
            }

            sql += ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in room variable
            while(resultSet.next())
            {
                roomList.add(new Room(resultSet.getInt("RoomNumber"),
                        resultSet.getShort("Occupancy"),
                        resultSet.getString("RoomType"),
                        resultSet.getBoolean("HasShower"),
                        resultSet.getBoolean("HasJacuzzi"),
                        resultSet.getBoolean("HasSeaView"),
                        resultSet.getBoolean( "IsFamilyRoom" ),
                        resultSet.getBoolean( "IsHoneymoonRoom" ),
                        resultSet.getShort( "Floor" ),
                        resultSet.getInt( "PoundsPerNight" )));

            }

            return roomList;
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
}