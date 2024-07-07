package utils;

import Models.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {

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
}

