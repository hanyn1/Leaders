package Controllers;


import Models.Article;
import Services.ServiceArticle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ArticleEditController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField contentField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private ServiceArticle serviceArticle;
    private Article article;

    public void setServiceArticle(ServiceArticle serviceArticle) {
        this.serviceArticle = serviceArticle;
    }

    public void setArticle(Article article) {
        this.article = article;
        titleField.setText(article.getTitre());
        descriptionField.setText(article.getDescription());
        contentField.setText(article.getContenu());
    }

    @FXML
    private void initialize() {
        saveButton.setOnAction(event -> saveArticle());
        cancelButton.setOnAction(event -> cancelEdit());
    }

    private void saveArticle() {
        try {
            article.setTitre(titleField.getText());
            article.setDescription(descriptionField.getText());
            article.setContenu(contentField.getText());

            serviceArticle.updateArticle(article);
            closeWindow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cancelEdit() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
