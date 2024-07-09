package utils;

import Models.Evenement;
import Models.RessourceEv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String dbURL = "jdbc:mysql://localhost:3306/evolearn";
    private static final String username = "root";
    private static final String password = "";


    public static ObservableList<Evenement> getEvenements() {
        ObservableList<Evenement> evenementsList = FXCollections.observableArrayList();

        // Obtenir la connexion depuis MyConfig
        Connection conn = MyConfig.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM evenements")) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String titre = rs.getString("titre");
                String description = rs.getString("description");

                evenementsList.add(new Evenement(id, titre, description));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return evenementsList;
    }

    public static ObservableList<RessourceEv> getRessourcesEv() {
            ObservableList<RessourceEv> ressourcesList = FXCollections.observableArrayList();

            // Obtenir la connexion depuis MyConfig
            Connection conn = MyConfig.getInstance().getConnection();

            if (conn == null) {
                System.err.println("Erreur: Connexion à la base de données non établie.");
                return ressourcesList; // Retourner une liste vide en cas d'erreur de connexion
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM ressources")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titre = rs.getString("titre");
                    String description = rs.getString("description");
                    String url = rs.getString("url");
                    Timestamp date = rs.getTimestamp("date_creation");

                    // Créer un objet RessourceEv à partir des données récupérées
                    RessourceEv ressource = new RessourceEv(id, titre, description, url, date);
                    ressourcesList.add(ressource);
                }

            } catch (SQLException e) {
                System.err.println("Erreur SQL lors de la récupération des ressources: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return ressourcesList;
        }

    public static void updateRessourceEv(RessourceEv ressources) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement stmt = conn.prepareStatement("UPDATE ressources SET titre = ?, description = ? WHERE id = ?")) {

            stmt.setString(1, ressources.getTitre());
            stmt.setString(2, ressources.getDescription());
            stmt.setInt(3, ressources.getId());

            stmt.executeUpdate();

            System.out.println("RessourceEv mise à jour avec succès dans la base de données.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de RessourceEv dans la base de données: " + e.getMessage());
        }
    }

    public static void deleteRessourceEv(RessourceEv selectedRessource) {
        String sql = "DELETE FROM ressourceev WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, selectedRessource.getId());

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("RessourceEv supprimée avec succès dans la base de données.");
            } else {
                System.out.println("Aucune ressource n'a été supprimée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de RessourceEv dans la base de données: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void deleteEvenement(Evenement ev) {
        String sqlSelect = "SELECT id FROM ressourceev WHERE id = ?";
        String sqlDelete = "DELETE FROM ressourceev WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
             PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {

            // Vérifier si l'événement existe
            selectStmt.setInt(1, ev.getId());
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // L'événement existe, donc on peut le supprimer
                deleteStmt.setInt(1, ev.getId());
                int rowsDeleted = deleteStmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Événement supprimé avec succès de la base de données.");
                } else {
                    System.out.println("Aucun événement n'a été supprimé.");
                }
            } else {
                System.out.println("L'événement avec l'ID spécifié n'existe pas.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'événement dans la base de données: " + e.getMessage());
        }
    }
       public static void updateEvenemets(Evenement ev) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement stmt = conn.prepareStatement("UPDATE ressources SET titre = ?, description = ? WHERE id = ?")) {

            stmt.setString(1, ev.getTitre());
            stmt.setString(2, ev.getDescription());
            stmt.setInt(3, ev.getId());

            stmt.executeUpdate();

            System.out.println("Ev mise à jour avec succès dans la base de données.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de Ev dans la base de données: " + e.getMessage());
        }
    }

}