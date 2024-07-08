package Services;

import Interfaces.ArticleInterface;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import Models.Article;
public class ServiceArticle  {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;
    //articleMap utilisée pour stocker les articles où la clé est l'identifiant de l'article (Long) et la valeur est l'article lui-même (Article).
    private Map<Long, Article> articleMap = new HashMap<>();
    public ServiceArticle(){
        this.connection= this.instance.getConnection();
        System.out.println("service Article");
    }
    // Méthode pour établir une connexion à la base de données
  /*  private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/evolearn";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }*/

    public void addArticle(Article article) throws SQLException {
        String query = "INSERT INTO Articles (titre, description, contenu) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {;
            stmt.setString(1, article.getTitre());
            stmt.setString(2, article.getDescription());
            stmt.setString(3, article.getContenu());
            stmt.executeUpdate();
        }
    }


    public Article getArticleById(Long id) {
        return null;
    }


    //noter l'article spécifié par articleId avec la note rating

    //recupere la note moyenne donné a un article

    public void rateArticle(Long articleId, int rating) throws SQLException {
        String query = "INSERT INTO ratings (article_id, rating) VALUES (?, ?) ";
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, articleId);
            preparedStatement.setInt(2, rating);
            preparedStatement.executeUpdate();
        }
    }


    public double getAverageRating(Long articleId) {
        Article article = articleMap.get(articleId);
        if (article != null) {
            return article.getAverageRating();
        }
        return 0.0;
    }

    public void updateArticle(Article article) throws SQLException {
        String query = "UPDATE Articles SET titre = ?, description = ?, contenu = ? WHERE id = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, article.getTitre());
            stmt.setString(2, article.getDescription());
            stmt.setString(3, article.getContenu());
            stmt.setLong(4, article.getId());

            stmt.executeUpdate();
        }
    }


    public List<Article> getByIndex() {
        return null;
    }


    public List<Article> getByIndex(Long id) {
        return null;
    }


    public void deleteArticle(long id) throws SQLException {
        String query = "DELETE FROM Articles WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Article> getAllArticles() throws SQLException{
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM Articles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitre(rs.getString("titre"));
                article.setDescription(rs.getString("description"));
                article.setContenu(rs.getString("contenu"));
                article.setDateCreation(rs.getTimestamp("date_creation"));
                articles.add(article);
            }
        }
        return articles;
    }

}
