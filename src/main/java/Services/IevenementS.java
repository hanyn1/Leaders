package Services;

import java.sql.SQLException;
import java.util.List;

public interface IevenementS<T> {
    void ajouter(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int id);
    List<T> recuperer() throws SQLException;
}

