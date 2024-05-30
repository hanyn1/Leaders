package Interfaces;

import java.util.List;

public interface workInterface<T> {
    void add(T t);
    List<T> getAll();
    void update(T t);
    void delete(T t);

}
