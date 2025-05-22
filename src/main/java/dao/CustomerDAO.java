/**
 *
 * This is a wrapper file used to handle interaction with Customer table in the database
 * @author Owen Walton
 * Current issue - Delete customer details function not active currently
 *                 whilst company decides what circumstance data is allowed to be deleted
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
                    + " WHERE CustomerId = " + pk
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();

            // store result of query in customer variable
            customer = new Customer(resultSet.getInt("CustomerId"),
                    resultSet.getString("Surname"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Title"),
                    resultSet.getDate("DOB"),
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
            sql = "SELECT COUNT(*) AS total FROM Customer;";

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
        List<Customer> customerList = new ArrayList<>();

        for (int i = 1; i <= getNumRows(); i++)
        {
            customerList.add(getByPK(i));
        }
        return customerList;
    }

    // ----------------------------------------------------------------------
    // Implementation of WriteOnlyDAO
    // ----------------------------------------------------------------------
    @Override
    public boolean insert(Customer customer)
    {
        // bRC stores whether insert has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "INSERT INTO Customer( "
                    + " Surname"
                    + ", FirstName"
                    + ", Title"
                    + ", DOB"
                    + ", HouseNumber"
                    + ", StreetName"
                    + ", City"
                    + ", Postcode"
                    + ", PhoneNumber"
                    + ", Email"
                    + " ) "
                    + " VALUES( "
                    + "\"" + customer.getSzSurname()  + "\""
                    + ", \"" + customer.getSzFirstName() + "\""
                    + ", \"" + customer.getSzTitle() + "\""
                    + ", \"" + customer.getDOB() + "\""
                    + ", \"" + customer.getSzHouseNumber() + "\""
                    + ", \"" + customer.getSzStreetName() + "\""
                    + ", \"" + customer.getSzCity() + "\""
                    + ", \"" + customer.getSzPostcode() + "\""
                    + ", \"" + customer.getSzPhoneNumber() + "\""
                    + ", \"" + customer.getSzEmail() + "\""
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
    public boolean update(Customer customer)
    {
        // bRC stores whether update has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "UPDATE Customer SET ";

            int count = 0; // can break out of function if all of customer's fields are null (nothing to update)

            // add all updates that aren't null
            if (customer.getSzSurname() != null)
            {
                sql = sql + "Surname = '" + customer.getSzSurname() + "', ";
                count++;
            }
            if (customer.getSzFirstName() != null)
            {
                sql = sql + "FirstName = '" + customer.getSzFirstName() + "', ";
                count++;
            }
            if (customer.getSzTitle() != null)
            {
                sql = sql + "Title = '" + customer.getSzTitle() + "', ";
                count++;
            }
            if (customer.getDOB() != null)
            {
                sql = sql + "DOB = '" + customer.getDOB() + "', ";
                count++;
            }
            if (customer.getSzHouseNumber() != null)
            {
                sql = sql + "HouseNumber = '" + customer.getSzHouseNumber() + "', ";
                count++;
            }
            if (customer.getSzStreetName() != null)
            {
                sql = sql + "StreetName = '" + customer.getSzStreetName() + "', ";
                count++;
            }
            if (customer.getSzCity() != null)
            {
                sql = sql + "City = '" + customer.getSzCity() + "', ";
                count++;
            }
            if (customer.getSzPostcode() != null)
            {
                sql = sql + "Postcode = '" + customer.getSzPostcode() + "', ";
                count++;
            }
            if (customer.getSzPhoneNumber() != null)
            {
                sql = sql + "PhoneNumber = '" + customer.getSzPhoneNumber() + "', ";
                count++;
            }
            if (customer.getSzEmail() != null)
            {
                sql = sql + "Email = '" + customer.getSzEmail() + "', ";
                count++;
            }

            if (count == 0)
            {
                return false;
            }

            // remove tail comma (and space)
            sql = sql.substring(0, sql.length() - 2);
            sql = sql + " WHERE CustomerId = " + customer.getICustomerId() + ";";

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
        // ------------------------------------- Currently doesn't work because customers are referenced in booking

        // bRC stores whether delete has worked
        boolean bRC = false;
        int iRC; // iRC is used to calculate bRC
        try
        {
            System.err.println( this.getClass().getName() + ": is DB connected? = " + dbConnection.isConnected() ) ;

            sql = "DELETE FROM Customer WHERE CustomerId = " + pk + ";";

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