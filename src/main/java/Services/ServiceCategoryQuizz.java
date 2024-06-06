package Services;

import Interfaces.QuizzInterface;
import Models.Quizz;
import Models.QuizzCategorie;
import utils.MyConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class ServiceCategoryQuizz implements QuizzInterface {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;
    public  ServiceCategoryQuizz(){
        this.connection= this.instance.getConnection();
        System.out.println("service");

}

    // Fonction pour ajouter une catégorie
    public void add(QuizzCategorie cat) {
        String req = "INSERT INTO `QuizzCategorie`(`id`, `nom`, `description`) VALUES ('" + cat.getId() + "','" + cat.getNom() + "','" + cat.getDescription() + "')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addQuizz(Quizz q) {

    }

    @Override
    public List<Quizz> getAllQuizzes() {
        return null;
    }

    @Override
    public Quizz getQuizzById(int id) {
        return null;
    }

    @Override
    public void updateQuizz(Quizz q) throws SQLException {

    }

    @Override
    public void deleteQuizz(int id) throws SQLException {

    }
    public class CategorieService {

        // Requêtes SQL pour les opérations CRUD
        private static final String ADD_CATEGORIE_SQL = "INSERT INTO categories (nom, description, date_creation, date_mise_a_jour) VALUES (?, ?, ?, ?)";
        private static final String UPDATE_CATEGORIE_SQL = "UPDATE categories SET nom = ?, description = ?, date_mise_a_jour = ? WHERE id = ?";
        private static final String DELETE_CATEGORIE_SQL = "DELETE FROM categories WHERE id = ?";

        // Objet Connection pour interagir avec la base de données
        private Connection connection;

        public CategorieService(Connection connection) {
            this.connection = connection;
        }

        // Fonction pour ajouter une catégorie
        public void addCategorie(QuizzCategorie categorie) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(ADD_CATEGORIE_SQL)) {
                statement.setString(1, categorie.getNom());
                statement.setString(2, categorie.getDescription());
                statement.executeUpdate();
            }
        }

        // Fonction pour mettre à jour une catégorie
        public void updateCategorie(QuizzCategorie categorie) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORIE_SQL)) {
                statement.setString(1, categorie.getNom());
                statement.setString(2, categorie.getDescription());

}
