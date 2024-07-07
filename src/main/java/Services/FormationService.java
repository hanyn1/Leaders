package Services;

import Models.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class FormationService {
    private final ObservableList<Formation> formations = FXCollections.observableArrayList();

    // Database connection parameters
    private final String url = "jdbc:mysql://localhost:3307/evolearn";
    private final String user = "root";
    private final String password = "root";

    public FormationService() {
        loadFormationsFromDatabase();
    }

    public void loadFormationsFromDatabase() {
        formations.clear(); // Clear existing formations before loading
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Formations")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");

                Formation formation = new Formation();
                formation.setId(id); // Set the id separately
                formation.setTitre(titre);
                formation.setDescription(description);

                formations.add(formation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Formation> getAllFormations() {
        return formations;
    }

    public Optional<Formation> getFormationById(int id) {
        return formations.stream()
                .filter(formation -> formation.getId() == id)
                .findFirst();
    }

    public void addFormation(Formation formation) {
        String query = "INSERT INTO Formations (titre, description) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, formation.getTitre());
            preparedStatement.setString(2, formation.getDescription());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        formation.setId(generatedKeys.getInt(1));
                        formations.add(formation);
                        System.out.println("Formation added: " + formation);
                    }
                }
            } else {
                System.err.println("Failed to add formation: " + formation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateFormation(int id, Formation updatedFormation) {
        String query = "UPDATE Formations SET titre = ?, description = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, updatedFormation.getTitre());
            preparedStatement.setString(2, updatedFormation.getDescription());
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                getFormationById(id).ifPresent(existingFormation -> {
                    existingFormation.setTitre(updatedFormation.getTitre());
                    existingFormation.setDescription(updatedFormation.getDescription());
                });
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFormation(int id) {
        String query = "DELETE FROM Formations WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                getFormationById(id).ifPresent(formations::remove);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
