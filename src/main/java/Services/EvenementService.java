package Services;
import Interfaces.evenementInterface;
import Models.Evenement;
import utils.MyConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements evenementInterface<Evenement> {
    private MyConfig instance = MyConfig.getInstance();
    private Connection connection;

    public EvenementService() {
        this.connection = instance.getConnection();
        System.out.println("Service Evenement");
    }

    @Override
    public void ajouter(Evenement evenement) {
        String sql = "INSERT INTO evenements (`titre`, `description`) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, evenement.getTitre());
            stmt.setString(2, evenement.getDescription());
            stmt.executeUpdate();
            System.out.println("Événement ajouté : " + evenement);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'événement : " + e.getMessage());
        }
    }

    @Override
    public void modifier(Evenement evenement) {
        String req = "UPDATE evenements SET titre = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, evenement.getTitre());
            ps.setString(2, evenement.getDescription());
            ps.setLong(3, evenement.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Événement modifié : " + evenement);
            } else {
                throw new RuntimeException("Événement non trouvé avec l'ID : " + evenement.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification de l'événement : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM evenements WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Événement supprimé avec l'ID : " + id);
            } else {
                throw new RuntimeException("Événement non trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'événement : " + e.getMessage());
        }
    }

    @Override
    public List<Evenement> recuperer() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT * FROM evenements";
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
            throw new RuntimeException("Erreur lors de la récupération des événements : " + e.getMessage());
        }
        return evenements;
    }

    public Evenement rechercherParId(int id) {
        String query = "SELECT * FROM evenements WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Evenement evenement = new Evenement();
                    evenement.setId(resultSet.getInt("id"));
                    evenement.setTitre(resultSet.getString("titre"));
                    evenement.setDescription(resultSet.getString("description"));
                    return evenement;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de l'événement par ID : " + e.getMessage());
        }
    }

    public boolean entiteExists(long id) {
        String checkSql = "SELECT COUNT(*) FROM entité WHERE id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setLong(1, id);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence de l'entité : " + e.getMessage());
        }
        return false;
    }

    public void ajouterEntite(long id, String titre) {
        String sql = "INSERT INTO entité (id, titre) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setString(2, titre);
            statement.executeUpdate();
            System.out.println("Entrée ajoutée dans la table 'entité' avec l'ID : " + id);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout dans la table 'entité' : " + e.getMessage());
        }
    }
}

