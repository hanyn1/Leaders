package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface coursInterface<T>  {
    void add(T t);
    List<T> getAll();
    void update(T t) throws SQLException;
    void delete(T t);

    void delete(int id) throws SQLException;
}
