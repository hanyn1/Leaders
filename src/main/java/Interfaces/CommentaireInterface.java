package Interfaces;

import Models.Commentaire;

import java.sql.SQLException;
import java.util.List;

public interface CommentaireInterface<T> {

        void add(T t);
        List<T> getAll();
      

        List<Commentaire> getByIndex();


        void updateCommentaire(T t) throws SQLException;


}
