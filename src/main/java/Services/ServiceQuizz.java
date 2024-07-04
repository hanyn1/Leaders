package Services;

import Interfaces.Quizzinterface;
import Models.Quizz;
import utils.MyConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuizz implements Quizzinterface<Quizz> {
    private Connection connection;

    public ServiceQuizz(){
        connection = MyConfig.getInstance().getConnection();
    }


    public void addQuizz(Quizz quizz) throws SQLException {
        String query = "INSERT INTO quizzs (title, description, option1, option2, option3, rightAnswer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, quizz.getTitre());
            statement.setString(2, quizz.getDescription());
            statement.setString(3, quizz.getOption1());
            statement.setString(4, quizz.getOption2());
            statement.setString(5, quizz.getOption3());
            statement.setString(6, quizz.getRightAnswer());
            statement.executeUpdate();
        }
    }

    public List<Quizz> getAllQuizzes() throws SQLException {
        List<Quizz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM quizzs";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Quizz quizz = new Quizz(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("option1"),
                        resultSet.getString("option2"),
                        resultSet.getString("option3"),
                        resultSet.getString("rightAnswer")
                );
                quizzes.add(quizz);
            }
        }
        return quizzes;
    }

    public void updateQuizz(Quizz quizz) throws SQLException {
        String query = "UPDATE quizzs SET title = ?, description = ?, option1 = ?, option2 = ?, option3 = ?, rightAnswer = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, quizz.getTitre());
            statement.setString(2, quizz.getDescription());
            statement.setString(3, quizz.getOption1());
            statement.setString(4, quizz.getOption2());
            statement.setString(5, quizz.getOption3());
            statement.setString(6, quizz.getRightAnswer());
            statement.setInt(8, quizz.getId());
            statement.executeUpdate();
        }
    }

    public void deleteQuizz(int id) throws SQLException {
        String query = "DELETE FROM quizzs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Quizz> recuperer() throws SQLException {
        return null;
    }
}