package Controllers;

import Models.Article;
import Services.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ManageArticlesController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField contentField;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, Integer> idColumn;
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
    private Article selectedArticle;

    public ManageArticlesController() {
        try {
            serviceArticle = new ServiceArticle();
            articleData.addAll(serviceArticle.getAllArticles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        articlesTable.setItems(articleData);

        addButton.setOnAction(event -> addArticle());
        updateButton.setOnAction(event -> openEditWindow());
        deleteButton.setOnAction(event -> deleteArticle());

        articlesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectArticle(newValue));
    }

    private void addArticle() {
        try {
            Article article = new Article();
            article.setTitre(titleField.getText());
            article.setDescription(descriptionField.getText());
            article.setContenu(contentField.getText());
            article.setDateCreation(new Timestamp(System.currentTimeMillis())); // Setting the current timestamp

            serviceArticle.addArticle(article);
            articleData.add(article);
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openEditWindow() {
        if (selectedArticle != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ArticleEdit.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Edit Article");

                ArticleEditController controller = loader.getController();
                controller.setServiceArticle(serviceArticle);
                controller.setArticle(selectedArticle);

                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(updateButton.getScene().getWindow());
                stage.showAndWait();

                articlesTable.refresh();
                clearForm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteArticle() {
        if (selectedArticle != null) {
            try {
                serviceArticle.deleteArticle(selectedArticle.getId());
                articleData.remove(selectedArticle);
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectArticle(Article article) {
        if (article != null) {
            selectedArticle = article;
            titleField.setText(article.getTitre());
            descriptionField.setText(article.getDescription());
            contentField.setText(article.getContenu());
        }
    }

    private void clearForm() {
        titleField.clear();
        descriptionField.clear();
        contentField.clear();
        selectedArticle = null;
    }
}
