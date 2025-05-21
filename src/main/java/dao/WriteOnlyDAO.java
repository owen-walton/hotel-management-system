/**
 * Implemented in the DAO's of all tables that allow for write access
 * @author Owen Walton
 */

package main.java.dao;

public interface WriteOnlyDAO<T>
{
    boolean insert(T t);
    boolean update(T t); // updates record with the same primary key as 't' to have all non null 't' values
    boolean delete(int pk);
}
