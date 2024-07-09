package Controllers;


import Models.Article;
import Models.Commentaire;
import Models.User;
import Services.ServiceArticle;
import Services.ServiceCommentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ArticleCommentController {

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
    private ServiceCommentaire serviceCommentaire;
    private Commentaire commentaire;
    private Article article;
    private User user;
    public void setServiceCommentaire(ServiceCommentaire serviceCommentaire) {
        this.serviceCommentaire = serviceCommentaire;
    }
    {

    }
    public void setArticle(Article article) {
        this.article = article;
        titleField.setText(article.getTitre());
        descriptionField.setText(article.getDescription());
        contentField.setText(article.getContenu());
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
        contentField.setText(commentaire.getContenu());
    }
    @FXML
    void addCommentaire(ActionEvent event) {

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
    private void saveComment() throws SQLException {
        commentaire.setContenu(contentField.getText());

        serviceCommentaire.add(article, commentaire, user);
        closeWindow();
    }

    private void cancelEdit() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
