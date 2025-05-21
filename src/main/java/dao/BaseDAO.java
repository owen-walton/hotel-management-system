package main.java.dao;

import java.util.List;

public interface BaseDAO<T>
{
    T getByPK(int pk);
    List<T> getAll();
    boolean insert(T t);
    boolean update(int pk, T t);
    boolean delete(int pk);
}
