package Interfaces;

import Models.Certifs;

import java.sql.SQLException;
import java.util.List;

public interface certifInterface<T> {
    void addCertif(T t) throws SQLException;
    List<T> getAllCertifs();

    Certifs getCertifById(T t);
}
