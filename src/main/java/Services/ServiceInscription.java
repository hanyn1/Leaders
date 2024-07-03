package Services;

import Interfaces.inscriptionInterface;
import Models.Inscription;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscription implements inscriptionInterface<Inscription> {
    private final Connection connection;

    public ServiceInscription() {
        this.connection = MyConfig.getInstance().getConnection();
        System.out.println("ServiceInscription is connected to the database.");
    }

    @Override
    public void addInscription(Inscription inscription) throws SQLException {
        String req = "INSERT INTO inscriptions (cours_id, utilisateur_id, expirationDate) VALUES (?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, inscription.getCoursId());
        ps.setInt(2, inscription.getUtilisateurId());
        ps.setTimestamp(3, inscription.getExpirationDate());

        ps.executeUpdate();
        System.out.println("Enrolling is done");
    }

    @Override
    public List<Inscription> getAllInscriptions() {
        List<Inscription> ins = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            String req = "SELECT * FROM inscriptions";
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Inscription i = new Inscription();
                i.setId(res.getInt("id"));
                i.setDate_creation(res.getTimestamp("date_creation"));
                i.setCoursId(res.getInt("cours_id"));
                i.setUtilisateurId(res.getInt("utilisateur_id"));
                i.setExpirationDate(res.getTimestamp("expirationDate"));

                ins.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ins;
    }

    @Override
    public Inscription getInscriptionById(int id) throws SQLException {
        // Implement this method if needed
        return null;
    }
    public void updateExpiredDate(Inscription i) throws SQLException {
        String sql = "UPDATE inscriptions SET expiration_date = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, i.getExpirationDate());
            statement.setInt(2, i.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating inscription expiration date.", e);
        }
    }

    public void update(Inscription cours) throws SQLException {
        String req = "UPDATE inscriptions SET date_creation=?, cours_id=?, utilisateur_id=?, expirationDate=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setTimestamp(1, cours.getDate_creation());
            ps.setInt(2, cours.getCoursId());
            ps.setInt(3, cours.getUtilisateurId());
            ps.setTimestamp(4, cours.getExpirationDate());
            ps.setInt(5, cours.getId());
            ps.executeUpdate();
        }
    }
    @Override
    public Inscription getInscriptionById(Inscription inscription) throws SQLException {
        String req = "SELECT * FROM inscriptions WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, inscription.getId());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Inscription i = new Inscription();
                i.setId(resultSet.getInt("id"));
                i.setDate_creation(resultSet.getTimestamp("date_creation"));
                i.setCoursId(resultSet.getInt("cours_id"));
                i.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                i.setExpirationDate(resultSet.getTimestamp("expirationDate"));
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Inscription getInscriptionByCourseAndUser(int courseId, int userId) {
        String req = "SELECT * FROM inscriptions WHERE cours_id = ? AND utilisateur_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, courseId);
            ps.setInt(2, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Inscription i = new Inscription();
                i.setId(resultSet.getInt("id"));
                i.setDate_creation(resultSet.getTimestamp("date_creation"));
                i.setCoursId(resultSet.getInt("cours_id"));
                i.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                i.setExpirationDate(resultSet.getTimestamp("expirationDate"));
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
