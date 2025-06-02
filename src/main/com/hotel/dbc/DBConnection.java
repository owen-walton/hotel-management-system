/**
 * This is to create and handle the SQL connection from within Java.
 * @author Owen Walton
 * Last edit: 21/05/25
 */

package main.com.hotel.dbc;

import java.sql.* ;

public class DBConnection
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // JDBC driver name and database URL
    // There is a bug/feature with Java and MySQL.
    // https://bugs.mysql.com/bug.php?id=93590 for explanation into bug
    // "useSSL=false" is a workaround for the bug
    final String DB_URL = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
    final String USER = "root"; // database username

    // code is designed to support running on system "codio.uk", these values make code specific to the codio database
    // final String PASS = "codio";

    // code also supports installation of mysql where root password is "mysqlaccess"
    final String PASS = "mysqlaccess";

    Connection conn = null;
    boolean bConnected ;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public DBConnection()
    {
        reset() ;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------
    private void reset()
    {
        this.bConnected = false ;
    }

    public void connect()
    {
        try
        {
            // Load the driver class explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( DB_URL , USER , PASS ) ;
            if ( conn != null )
            {
                this.bConnected = true ;
            }
        }
        catch ( SQLException se )
        {
            System.err.println( this.getClass().getName() + "SQL error: " + se ) ;
            se.printStackTrace() ;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + "Error: " + e ) ;
            e.printStackTrace() ;
        }
    }

    public void disconnect()
    {
        try
        {
            this.conn.close() ;
            this.bConnected = false ;
        }
        catch ( SQLException se )
        {
            System.err.println( this.getClass().getName() + "SQL error: " + se ) ;
            se.printStackTrace() ;
        }
        catch ( Exception e )
        {
            System.err.println( this.getClass().getName() + "Error: " + e ) ;
            e.printStackTrace() ;
        }
    }

    public void commit()
    {
        // potential later addition to handle concurrent access
    }

    public void rollback()
    {
        // allow for the failing conditions as potential later addition to handle concurrent access
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------
    public boolean isConnected()
    {
        return ( this.bConnected ) ;
    }

    public Connection getConnection()
    {
        return ( this.conn ) ;
    }

    // ----------------------------------------------------------------------
    // test rig
    // ----------------------------------------------------------------------
    public static void main( String [] args )
    {
        DBConnection foo = new DBConnection() ;
        foo.connect() ;
        if ( foo.isConnected() )
        {
            System.out.println( "Now talking to DB" ) ;
        }
        else
        {
            System.out.println( "Failed to talk to DB." ) ;
        }
        foo.disconnect() ;
    }
}