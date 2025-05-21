/**
 *
 * This is a wrapper file used to handle interaction with Customer table in the database
 * @author Owen Walton
 *
 **/

package main.java.dao;

import main.java.model.Customer;
import main.java.dbc.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ReadOnlyDAO<Customer>, WriteOnlyDAO<Customer>{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    // DB materials.
    private String sql;
    private String sz;

    private Statement sqlStatement = null;
    private ResultSet resultSet = null;

    private DBConnection dbConnection;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public CustomerDAO()
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
        sz = null;
        sql = null;
        sqlStatement = null;
        resultSet = null;
    }

    // ----------------------------------------------------------------------
    // Implementation of ReadOnlyDAO
    // ----------------------------------------------------------------------
    @Override
    public Customer getByPK(int pk)
    {
        Customer customer;

        try {
            System.err.println(this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected());

            // create query
            sql = "SELECT CustomerId"
                    + ", Surname"
                    + ", FirstName"
                    + ", DOB"
                    + ", Title"
                    + ", HouseNumber"
                    + ", StreetName"
                    + ", City"
                    + ", Postcode"
                    + ", PhoneNumber"
                    + ", Email"
                    + " FROM Customer"
                    + " WHERE CustomerId = " + pk;

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in customer variable
            customer = new Customer(resultSet.getInt("CustomerId"),
                    resultSet.getString("Surname"),
                    resultSet.getString("FirstName"),
                    resultSet.getDate("DOB"),
                    resultSet.getString("Title"),
                    resultSet.getString("HouseNumber"),
                    resultSet.getString( "StreetName" ),
                    resultSet.getString( "City" ),
                    resultSet.getString( "Postcode" ),
                    resultSet.getString( "PhoneNumber" ),
                    resultSet.getString( "Email" ) );

            return customer;
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
            sql = "SELECT COUNT(*) AS total FROM Customer";

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
    public List<Customer> getAll()
    {
        List<Customer> customerList = new ArrayList<Customer>();

        for (int i = 1; i <= getNumRows(); i++)
        {
            customerList.add(getByPK(i));
        }
        return customerList;
    }

}