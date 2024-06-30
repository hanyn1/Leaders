package Controllers;

import Models.Article;
import Services.ServiceArticle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AfficherArticle {

    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, String> titleColumn;
    @FXML
    private TableColumn<Article, String> descriptionColumn;
    @FXML
    private TableColumn<Article, String> contentColumn;
    @FXML
    private TableColumn<Article, Timestamp> dateColumn;

    private ObservableList<Article> articleData = FXCollections.observableArrayList();
    private ServiceArticle serviceArticle;

    public AfficherArticle() {
        try {
            articleData.addAll(serviceArticle.getAllArticles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Initialize method to populate the table
    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        articlesTable.setItems(articleData);
    }
}
