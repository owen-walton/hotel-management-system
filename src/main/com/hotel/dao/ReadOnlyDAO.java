/**
 * Implemented in the DAO's of all tables that allow for read access
 * @author Owen Walton
 */

package main.com.hotel.dao;

import java.util.List;

public interface ReadOnlyDAO<T>
{
    T getByPK(int pk);
    List<T> getAll();
    int getNumRows();
}