package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Quizzinterface <T>{
     void addQuizz(T t)throws SQLException;
     void updateQuizz (T t) throws SQLException;
     void deleteQuizz(int id) throws SQLException;
     List<T> recuperer() throws SQLException;

}
