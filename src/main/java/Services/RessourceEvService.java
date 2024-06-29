package Services;

import Interfaces.ressourceEvInterface;
import Models.RessourceEv;
import utils.MyConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RessourceEvService implements ressourceEvInterface<RessourceEv> {
    private Connection connection;

    public RessourceEvService() throws SQLException {
        connection = MyConfig.getInstance().getConnection();
    }

    @Override
    public void ajouter(RessourceEv ressourceEv) throws SQLException {
        String sql = "INSERT INTO ressourceEv (id, titre, url, type, evenement_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, ressourceEv.getId());
            statement.setString(2, ressourceEv.getTitre());
            statement.setString(3, ressourceEv.getUrl());
            statement.setString(4, ressourceEv.getType());
            statement.setLong(5, ressourceEv.getEvenementId());
            statement.executeUpdate();
            System.out.println("RessourceEv ajoutée : " + ressourceEv);
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la RessourceEv : " + e.getMessage());
        }
    }


    @Override
    public void modifier(RessourceEv ressourceEv) throws SQLException {
        String req = "UPDATE ressourceEv SET titre = ?, url = ?, type = ?, evenement_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, ressourceEv.getTitre());
            ps.setString(2, ressourceEv.getUrl());
            ps.setString(3, ressourceEv.getType());
            ps.setLong(4, ressourceEv.getEvenementId());
            ps.setLong(5, ressourceEv.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("RessourceEv modifiée : " + ressourceEv);
            } else {
                System.out.println("RessourceEv non trouvée avec l'ID : " + ressourceEv.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la RessourceEv : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM ressourceEv WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("RessourceEv supprimée avec l'ID : " + id);
            } else {
                System.out.println("RessourceEv non trouvée avec l'ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la RessourceEv : " + e.getMessage());
        }
    }

    @Override
    public List<RessourceEv> recuperer() throws SQLException {
        List<RessourceEv> ressourcesEv = new ArrayList<>();
        String sql = "SELECT * FROM ressourceEv";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String titre = resultSet.getString("titre");
                String url = resultSet.getString("url");
                String type = resultSet.getString("type");
                long evenement_id = resultSet.getLong("evenement_id");
                RessourceEv ressourceEv = new RessourceEv(id, titre, url, type, evenement_id);
                ressourcesEv.add(ressourceEv);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RessourcesEv : " + e.getMessage());
        }
        return ressourcesEv;
    }
}


