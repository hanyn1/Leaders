package Services;

import Models.Temoignage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class ServiceTemoignage {
    private final MyConfig myConfig;

    public ServiceTemoignage(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    public ObservableList<Temoignage> getAllTemoignages() {
        ObservableList<Temoignage> temoignages = FXCollections.observableArrayList();
        String query = "SELECT * FROM Temoignages";

        try (Connection connection = myConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Temoignage temoignage = new Temoignage();
                temoignage.setId(resultSet.getInt("id"));
                temoignage.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                temoignage.setContenu(resultSet.getString("contenu"));
                temoignage.setDateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime());
                temoignages.add(temoignage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temoignages;
    }

    public void addTemoignage(Temoignage temoignage) {
        String query = "INSERT INTO Temoignages (utilisateur_id, contenu, date_creation) VALUES (?, ?, ?)";

        try (Connection connection = myConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, temoignage.getUtilisateurId());
            preparedStatement.setString(2, temoignage.getContenu());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                temoignage.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTemoignage(int temoignageId, Temoignage updatedTemoignage) {
        String query = "UPDATE Temoignages SET utilisateur_id=?, contenu=?, date_creation=? WHERE id=?";

        try (Connection connection = myConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, updatedTemoignage.getUtilisateurId());
            preparedStatement.setString(2, updatedTemoignage.getContenu());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(updatedTemoignage.getDateCreation()));
            preparedStatement.setInt(4, temoignageId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteTemoignage(int temoignageId) {
        String query = "DELETE FROM Temoignages WHERE id=?";

        try (Connection connection = myConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, temoignageId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Temoignage> getTemoignageById(int temoignageId) {
        String query = "SELECT * FROM Temoignages WHERE id=?";

        try (Connection connection = myConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, temoignageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Temoignage temoignage = new Temoignage();
                temoignage.setId(resultSet.getInt("id"));
                temoignage.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                temoignage.setContenu(resultSet.getString("contenu"));
                temoignage.setDateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime());
                return Optional.of(temoignage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
