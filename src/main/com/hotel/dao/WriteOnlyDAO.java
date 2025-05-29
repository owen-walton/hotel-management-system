/**
 * Implemented in the DAO's of all tables that allow for write access
 * @author Owen Walton
 */

package main.com.hotel.dao;

public interface WriteOnlyDAO<T>
{
    int insert(T t); // returns id created
    boolean update(T t); // updates record with the same primary key as 't' to have all non null 't' values
    boolean delete(int pk);
}
