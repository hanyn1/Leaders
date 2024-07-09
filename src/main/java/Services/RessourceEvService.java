package Services;

import Models.RessourceEv;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceEvService {

    public void ajouter(RessourceEv ressource) throws SQLException {
        Connection conn = MyConfig.getInstance().getConnection();
        String query = "INSERT INTO ressources (titre, description, url, date_creation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, ressource.getTitre());
            ps.setString(2, ressource.getDescription());
            ps.setString(3, ressource.getUrl());
            ps.setTimestamp(4, ressource.getDateCreation());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la RessourceEv : " + e.getMessage());
        }
    }

    public void modifier(RessourceEv ressource) throws SQLException {
        Connection conn = MyConfig.getInstance().getConnection();
        String query = "UPDATE ressources SET titre = ?, description = ?, url = ?, date_creation = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, ressource.getTitre());
            ps.setString(2, ressource.getDescription());
            ps.setString(3, ressource.getUrl());
            ps.setTimestamp(4, ressource.getDateCreation());
            ps.setInt(5, ressource.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la RessourceEv : " + e.getMessage());
        }
    }

    public void supprimer(int id) throws SQLException {
        Connection conn = MyConfig.getInstance().getConnection();
        String query = "DELETE FROM ressources WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la RessourceEv : " + e.getMessage());
        }
    }

    public List<RessourceEv> recuperer() throws SQLException {
        Connection conn = MyConfig.getInstance().getConnection();
        String query = "SELECT * FROM ressources";
        List<RessourceEv> ressources = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                RessourceEv ressource = new RessourceEv(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getString("url"),
                        rs.getTimestamp("date_creation")
                );
                ressources.add(ressource);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RessourcesEv : " + e.getMessage());
        }
        return ressources;
    }
}




