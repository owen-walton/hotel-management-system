/**
 *
 * This is a wrapper file used to handle interaction with Room table in the database
 * @author Owen Walton
 *
 **/

package main.java.dao;

import main.java.model.Room;
import main.java.dbc.DBConnection;

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
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

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

            // store result of query in customer variable
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
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

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

        for (int i = 1; i <= getNumRows(); i++)
        {
            roomList.add(getByPK(i));
        }
        return roomList;
    }

}