package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface workInterface<T> {
    void add(T t);
    List<T> getAll();
    void update(T t) throws SQLException;
    void delete(T t);

}
