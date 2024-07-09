package Controllers;

import Services.ServiceArticle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import Models.Article;
public class InstructorArticle {
    private Stage stage;
    @FXML
    private VBox ArticlesVBox;
    private Scene scene;
    @FXML
    private AnchorPane main_form;
    private ServiceArticle serviceArticle;

    @FXML
    public void initialize() {
        serviceArticle = new ServiceArticle();
        displayArticles();
    }
    private HBox createArticleCard(Article article) {
        HBox card = new HBox(10);
        card.setStyle("-fx-padding: 10px;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");
        card.setPrefWidth(400);

        VBox detailsVBox = new VBox(5);
        Label titleLabel = new Label("Titre: " + article.getTitre());
        Label descriptionLabel = new Label("Description: " + article.getDescription());
        Label averageRatingLabel = new Label("Note Moyenne: " + String.format("%.2f", article.getAverageRating()));
        TextField ratingField = new TextField();
        ratingField.setPromptText("Entrer une note (1-5)");
        Button submitRatingButton = new Button("Soumettre la Note");

        submitRatingButton.setOnAction(event -> {
            try {
                int rating = Integer.parseInt(ratingField.getText());
                if (rating >= 1 && rating <= 5) {
                    serviceArticle.rateArticle(article.getId(), rating); // Insère l'évaluation dans la base de données
                    article.addRating(rating); // Ajoute la note à l'article
                    averageRatingLabel.setText("Note Moyenne: " + String.format("%.2f", article.getAverageRating())); // Met à jour l'affichage de la note moyenne
                } else {
                    showAlert("Erreur", "La note doit être entre 1 et 5.");
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Entrée de note invalide.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel, averageRatingLabel, ratingField, submitRatingButton);
        card.getChildren().add(detailsVBox);

        return card;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void displayArticles() {
        ArticlesVBox.getChildren().clear();
        try {
            List<Article> articles = serviceArticle.getAllArticles();
            for (Article article : articles) {
                HBox card = createArticleCard(article);
                ArticlesVBox.getChildren().add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void rateArticle(long articleId, int rating) throws SQLException {
        serviceArticle.rateArticle(articleId, rating);
    }
    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }



    public void gotLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorPage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void goToFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FormationController.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantEvents.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomeQ.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
