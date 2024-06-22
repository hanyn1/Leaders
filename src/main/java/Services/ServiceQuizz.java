package Services;

import Interfaces.Quizzinterface;
import Models.Quizz;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuizz implements Quizzinterface<Quizz> {
    private Connection connection;

    public ServiceQuizz(){
        connection = MyConfig.getInstance().getConnection();
    }

    @Override
    public void addQuizz(Quizz q) throws SQLException {
        String req = "INSERT INTO quizzs (`titre`, `description`, `date_creation`) VALUES ('"+q.getTitre()+"','"+q.getDescription()+"','"+q.getDate()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void updateQuizz(Quizz q) throws SQLException {
        String req= "UPDATE quizzs SET titre = ?, description = ?, date_creation = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setString(1, q.getTitre());
        us.setString(2, q.getDescription());
        us.setDate(3, q.getDate());
        us.executeUpdate();

    }

    @Override
    public void deleteQuizz(int id) throws SQLException {
        String req = "DELETE FROM quizzs WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1,id);
        us.executeUpdate();

    }

    @Override
    public List<Quizz> recuperer() throws SQLException {
        List<Quizz> quizzs = new ArrayList<>();
        String req = "SELECT * FROM quizzs";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Quizz q = new Quizz();
            q.setId(rs.getInt("id"));
            q.setTitre(rs.getString("titre"));
            q.setDescription(rs.getString("description"));
            q.setDate(rs.getDate("date_creation"));

            quizzs.add(q);
        }
        return quizzs;
    }


}
