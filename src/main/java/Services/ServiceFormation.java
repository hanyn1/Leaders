package Services;

import Models.Formation;
import utils.MyConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ServiceFormation {
    private final ObservableList<Formation> formations = FXCollections.observableArrayList();
    private final Connection connection;

    public ServiceFormation() {
        connection = MyConfig.getInstance().getConnection();
        loadFormationsFromDatabase();
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
        try (PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, formation.getTitre());
            pstmt.setString(2, formation.getDescription());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                formation.setId(rs.getInt(1));
                formations.add(formation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateFormation(int id, Formation updatedFormation) {
        String query = "UPDATE Formations SET titre = ?, description = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, updatedFormation.getTitre());
            pstmt.setString(2, updatedFormation.getDescription());
            pstmt.setInt(3, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Optional<Formation> existingFormation = getFormationById(id);
                if (existingFormation.isPresent()) {
                    existingFormation.get().setTitre(updatedFormation.getTitre());
                    existingFormation.get().setDescription(updatedFormation.getDescription());
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFormation(int id) {
        String query = "DELETE FROM Formations WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Optional<Formation> formationToDelete = getFormationById(id);
                return formationToDelete.map(formations::remove).orElse(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadFormationsFromDatabase() {
        String query = "SELECT * FROM Formations";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String description = rs.getString("description");

                Formation formation = new Formation(id, titre, description);
                formations.add(formation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
