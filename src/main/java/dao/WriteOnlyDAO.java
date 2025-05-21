/**
 * Implemented in the DAO's of all tables that allow for write access
 * @author Owen Walton
 */

package main.java.dao;

public interface WriteOnlyDAO<T>
{
    boolean insert(T t);
    boolean update(int pk, T t);
    boolean delete(int pk);
}
