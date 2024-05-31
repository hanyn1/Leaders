package Services;

import Models.Quizz;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuizz {

    private MyConfig instance = MyConfig.getInstance();
    private Connection connection;

    public ServiceQuizz() {
        this.connection = instance.getConnection();
        System.out.println("ServiceQuizz initialized");
    }

    // Add a new Quizz
    public void addQuizz(Quizz q) {
        String req = "INSERT INTO Quizz(id, titre, description, date) VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, q.getId());
            ps.setString(2, q.getTitre());
            ps.setString(3, q.getDescription());
            ps.setDate(4, new java.sql.Date(q.getDate().getTime()));

            ps.executeUpdate();
            System.out.println("Quizz added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all Quizzes
    public List<Quizz> getAllQuizzes() {
        List<Quizz> quizzes = new ArrayList<>();
        String req = "SELECT id, titre, description, date FROM Quizz";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                Quizz quizz = new Quizz(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
                quizzes.add(quizz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    // Get a Quizz by ID
    public Quizz getQuizzById(int id) {
        Quizz quizz = null;
        String req = "SELECT id, titre, description, date FROM Quizz WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quizz = new Quizz(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizz;
    }

    // Update an existing Quizz
    public void updateQuizz(Quizz q) {
        String req = "UPDATE Quizz SET titre = ?, description = ?, date = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, q.getTitre());
            ps.setString(2, q.getDescription());
            ps.setDate(3, new java.sql.Date(q.getDate().getTime()));
            ps.setInt(4, q.getId());

            ps.executeUpdate();
            System.out.println("Quizz updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a Quizz by ID
    public void deleteQuizz(int id) {
        String req = "DELETE FROM Quizz WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Quizz deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
