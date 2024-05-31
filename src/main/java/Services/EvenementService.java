package Services;

import Models.Evenement;
import UTILS.MyConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements IevenementS<Evenement> {
    private Connection connection;

    public EvenementService() throws SQLException {
        connection = MyConfig.getInstance().getConnection();
    }

    @Override
    public void ajouter(Evenement evenement) throws SQLException {
        if (!entiteExists(evenement.getId())) {
            ajouterEntite(evenement.getId(), evenement.getTitre());
        }

        String sql = "INSERT INTO evenement (id, titre, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, evenement.getId());
            statement.setString(2, evenement.getTitre());
            statement.setString(3, evenement.getDescription());
            statement.executeUpdate();
            System.out.println("Événement ajouté : " + evenement);
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'événement : " + e.getMessage());
        }
    }

    @Override
    public void modifier(Evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET titre = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, evenement.getTitre());
            ps.setString(2, evenement.getDescription());
            ps.setLong(3, evenement.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Événement modifié : " + evenement);
            } else {
                System.out.println("Événement non trouvé avec l'ID : " + evenement.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'événement : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM evenement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Événement supprimé avec l'ID : " + id);
            } else {
                System.out.println("Événement non trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'événement : " + e.getMessage());
        }
    }

    @Override
    public List<Evenement> recuperer() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT * FROM evenement";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                Evenement evenement = new Evenement(id, titre, description);
                evenements.add(evenement);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des événements : " + e.getMessage());
        }
        return evenements;
    }

    private boolean entiteExists(long id) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM entité WHERE id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setLong(1, id);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void ajouterEntite(long id, String titre) throws SQLException {
        String sql = "INSERT INTO entité (id, titre) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setString(2, titre);
            statement.executeUpdate();
            System.out.println("Entrée ajoutée dans la table 'entité' avec l'ID : " + id);
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout dans la table 'entité' : " + e.getMessage());
        }
    }
}

