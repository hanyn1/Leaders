package Interfaces;

import Models.Inscription;

import java.sql.SQLException;
import java.util.List;

public interface inscriptionInterface<T> {

    void addInscription(T t) throws SQLException;
    List<T> getAllInscriptions();

    Inscription getInscriptionById(T t);

}
