package Services;

import Interfaces.Quizzinterface;
import Models.Quizz;
import Models.QuizzCat;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizzCatService implements Quizzinterface <QuizzCat> {
    private Connection connection;

    public QuizzCatService(){
        connection = MyConfig.getInstance().getConnection();
    }

    @Override
    public void addQuizz(QuizzCat quizzCat) throws SQLException {
        String req = "INSERT INTO quizzcategorie (`nom`, `description`) VALUES ('"+quizzCat.getNom()+"','"+quizzCat.getDescription()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }


    @Override
    public void updateQuizz(QuizzCat quizzCat) throws SQLException {
        String req= "UPDATE quizzcategorie SET nom = ?, description = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setString(1, quizzCat.getNom());
        us.setString(2, quizzCat.getDescription());
        us.executeUpdate();

    }


    @Override
    public void deleteQuizz(int id) throws SQLException {
        String req = "DELETE FROM quizzcategorie WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1,id);
        us.executeUpdate();

    }


    @Override
    public List<QuizzCat> recuperer() throws SQLException {
        List<QuizzCat> quizzcategorie = new ArrayList<>();
        String req = "SELECT * FROM quizzs";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            QuizzCat quizzCat = new QuizzCat();
            quizzCat.setId(rs.getInt("id"));
            quizzCat.setNom(rs.getString("nom"));
            quizzCat.setDescription(rs.getString("description"));


            quizzcategorie.add(quizzCat);
        }
        return quizzcategorie;
    }
}
