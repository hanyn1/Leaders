package Controllers;
import org.controlsfx.control.Rating;
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

public class EtudiantArticle {

    @FXML
    private VBox ArticlesVBox;

    @FXML
    private AnchorPane main_form;
    private ServiceArticle serviceArticle;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rating note;
    @FXML
    public void initialize() {
        serviceArticle = new ServiceArticle();
        displayArticles();
    }
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("Rating given by user"+note.getRating());
    }

    public void start(Stage primaryStage) {
        HBox card = createArticleCard(new Article("Exemple Titre", "Exemple Description","Exemple content", 3));

        Scene scene = new Scene(card, 600, 400);
        primaryStage.setTitle("Article Card Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createArticleCard(Article article) {
        HBox card = new HBox(10);
        card.setStyle("-fx-padding: 10px;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color:#4e4406");
        card.setPrefWidth(400);

        VBox detailsVBox = new VBox(5);
        Label titleLabel = new Label("Titre: " + article.getTitre());
        Label descriptionLabel = new Label("Description: " + article.getDescription());

        // Ajouter le composant Rating
        Rating rating = new Rating();
        rating.setMax(5); // Exemple: une échelle de 1 à 5 étoiles
        rating.setRating(article.getRating()); // Pré-remplir avec la note existante si disponible

        Button submitRatingButton = new Button("Soumettre la Note");
        submitRatingButton.setStyle("-fx-border-color:#4e4406;-fx-background-color: #4e4406; -fx-text-fill: white");
        submitRatingButton.setOnAction(event -> {
            double newRating = rating.getRating();
            System.out.println("New Rating: " + newRating);
            // Logique pour soumettre la note, par exemple :
            article.setRating(newRating);
            // Appeler le service pour sauvegarder la note, etc.
        });

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel, rating, submitRatingButton);
        card.getChildren().add(detailsVBox);

        return card;
    }

    /*private HBox createArticleCard(Article article) {
        HBox card = new HBox(10);
        card.setStyle("-fx-padding: 10px;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color:#4e4406");
        card.setPrefWidth(400);

        VBox detailsVBox = new VBox(5);
        Label titleLabel = new Label("Titre: " + article.getTitre());
        Label descriptionLabel = new Label("Description: " + article.getDescription());

        // Ajouter le composant Rating
        Rating rating = new Rating();
        rating.setMax(5); // Exemple: une échelle de 1 à 5 étoiles
        rating.setRating(article.getRating()); // Pré-remplir avec la note existante si disponible

        Button submitRatingButton = new Button("Soumettre la Note");
        submitRatingButton.setStyle("-fx-border-color:#4e4406;-fx-background-color: #4e4406; -fx-text-fill: white");
        submitRatingButton.setOnAction(event -> {
            double newRating = rating.getRating();
            // Logique pour soumettre la note, par exemple :
            article.setRating(newRating);
            // Appeler le service pour sauvegarder la note, etc.
        });

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel, rating, submitRatingButton);
        card.getChildren().add(detailsVBox);

        return card;
    }*/
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
