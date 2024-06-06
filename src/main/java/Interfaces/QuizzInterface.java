package Interfaces;

import Models.Article;
import Models.Quizz;

import java.sql.SQLException;
import java.util.List;

public interface QuizzInterface <T>{
   void addQuizz(Quizz q) ;
    List<Quizz> getAllQuizzes();
    Quizz getQuizzById(int id);
    void updateQuizz(Quizz q) throws SQLException;
    void deleteQuizz (int id)throws SQLException;



}
