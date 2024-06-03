package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface inscriptionInterface<T> {

    void addInscription(T t);
    List<T> getAllInscriptions();
    void update(T t) throws SQLException;
    void delete(T t);


}
