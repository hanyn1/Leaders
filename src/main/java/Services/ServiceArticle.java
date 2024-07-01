package Services;

import Models.Article;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceArticle {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;

    public ServiceArticle(){
        this.connection= this.instance.getConnection();
        System.out.println("service Article");
    }

    public void addArticle(Article article) throws SQLException {
        String query = "INSERT INTO Articles (titre, description, contenu) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {;
            stmt.setString(1, article.getTitre());
            stmt.setString(2, article.getDescription());
            stmt.setString(3, article.getContenu());
            stmt.executeUpdate();
        }
    }

    public void updateArticle(Article article) throws SQLException {
        String query = "UPDATE Articles SET titre = ?, description = ?, contenu = ? WHERE id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, article.getTitre());
            stmt.setString(2, article.getDescription());
            stmt.setString(3, article.getContenu());
            stmt.setInt(4, article.getId());

            stmt.executeUpdate();
        }
    }


    public void deleteArticle(int id) throws SQLException {
        String query = "DELETE FROM Articles WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Article> getAllArticles() throws SQLException {
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
