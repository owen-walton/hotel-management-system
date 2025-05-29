/**
 *
 * This is a wrapper file used to handle interaction with Customer table in the database
 * @author Owen Walton
 * Current issue - Delete customer details function not active currently
 *                 whilst company decides what circumstance data is allowed to be deleted
 *
 **/

package main.com.hotel.dao;

import com.mysql.cj.protocol.Resultset;
import main.com.hotel.model.criteria.CustomerSearchDetails;
import main.com.hotel.model.entity.Customer;
import main.com.hotel.dbc.DBConnection;
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
                    + ", IsActive"
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
                    resultSet.getString( "Email" ),
                    resultSet.getBoolean("IsActive"));

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
                    + ", IsActive"
                    + " FROM Customer"
                    + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in customer variable
            while (resultSet.next())
            {
                customerList.add(new Customer(resultSet.getInt("CustomerId"),
                        resultSet.getString("Surname"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("Title"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("HouseNumber"),
                        resultSet.getString( "StreetName" ),
                        resultSet.getString( "City" ),
                        resultSet.getString( "Postcode" ),
                        resultSet.getString( "PhoneNumber" ),
                        resultSet.getString( "Email" ),
                        resultSet.getBoolean("IsActive")));
            }

            return customerList;
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

    public List<Customer> getByCustomerDetails(CustomerSearchDetails details)
    {
        List<Customer> customerList = new ArrayList<>();

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
                    + ", IsActive"
                    + " FROM Customer"
                    + " WHERE";

            boolean conditionAdded = false;
            if (details.szSurname() != null)
            {
                sql = sql + " AND LOWER(Surname) = LOWER('" + details.szSurname() + "') ";
                conditionAdded = true;
            }
            if(details.DOB() != null)
            {
                sql = sql + " AND DOB = '" + details.DOB() + "'";
                conditionAdded = true;
            }
            if(details.szEmail() != null)
            {
                sql = sql + " AND LOWER(Email) = LOWER('" + details.szEmail() + "')";
                conditionAdded = true;
            }
            if(details.szPhoneNumber() != null)
            {
                sql = sql + " AND PhoneNumber = '" + details.szPhoneNumber() + "'";
                conditionAdded = true;
            }

            if(!conditionAdded)
            {
                return null;
            }

            sql = sql.replaceAll("WHERE AND", "WHERE");
            sql = sql + ";";

            // execute query
            sqlStatement = dbConnection.getConnection().createStatement();
            resultSet = sqlStatement.executeQuery(sql);

            // store result of query in customer variable
            while (resultSet.next())
            {
                customerList.add(new Customer(resultSet.getInt("CustomerId"),
                        resultSet.getString("Surname"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("Title"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("HouseNumber"),
                        resultSet.getString( "StreetName" ),
                        resultSet.getString( "City" ),
                        resultSet.getString( "Postcode" ),
                        resultSet.getString( "PhoneNumber" ),
                        resultSet.getString( "Email" ),
                        resultSet.getBoolean("IsActive")));
            }

            return customerList;
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
    public int insert(Customer customer)
    {
        // iRC stores whether insert has worked
        int iRC;
        int newCustomerId = -1;
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
                    + ", IsActive"
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
                    + ", " + customer.getIsActive()
                    + " ) ; " ;

            sqlStatement = dbConnection.getConnection().createStatement() ;
            iRC = sqlStatement.executeUpdate( sql , Statement.RETURN_GENERATED_KEYS ) ;

            // iRC will hold how many records were updated or inserted.  zero is bad in this case.
            if ( iRC == 1 )
            {
                ResultSet rs = sqlStatement.getGeneratedKeys();
                if(rs.next())
                {
                    newCustomerId = rs.getInt(1);
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

        return newCustomerId;
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

            // add all updates that aren't null
            if (customer.getSzSurname() != null)
            {
                sql = sql + "Surname = '" + customer.getSzSurname() + "', ";
            }
            if (customer.getSzFirstName() != null)
            {
                sql = sql + "FirstName = '" + customer.getSzFirstName() + "', ";
            }
            if (customer.getSzTitle() != null)
            {
                sql = sql + "Title = '" + customer.getSzTitle() + "', ";
            }
            if (customer.getDOB() != null)
            {
                sql = sql + "DOB = '" + customer.getDOB() + "', ";
            }
            if (customer.getSzHouseNumber() != null)
            {
                sql = sql + "HouseNumber = '" + customer.getSzHouseNumber() + "', ";
            }
            if (customer.getSzStreetName() != null)
            {
                sql = sql + "StreetName = '" + customer.getSzStreetName() + "', ";
            }
            if (customer.getSzCity() != null)
            {
                sql = sql + "City = '" + customer.getSzCity() + "', ";
            }
            if (customer.getSzPostcode() != null)
            {
                sql = sql + "Postcode = '" + customer.getSzPostcode() + "', ";
            }
            if (customer.getSzPhoneNumber() != null)
            {
                sql = sql + "PhoneNumber = '" + customer.getSzPhoneNumber() + "', ";
            }
            if (customer.getSzEmail() != null)
            {
                sql = sql + "Email = '" + customer.getSzEmail() + "', ";
            }
            // no null value for IsActive so no check is done and is always updated
            sql = sql + "IsActive = " + customer.getIsActive();

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

    // any occurrence of pk (customerId) in Booking must be deleted first (handled in service layer)
    @Override
    public boolean delete(int pk)
    {
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