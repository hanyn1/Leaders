package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class WelcomeEv {

    @FXML
    private Button AfficherEv;

    @FXML
    private Button AjouterBoutton;

    @FXML
    private Button MiseAjourEv;

    @FXML
    private Button SupprimerEv;

    @FXML
    private Label TextWelcome;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchForm(ActionEvent actionEvent) {
    }

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterCoursAdmin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RoleCRUD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AfficherArticle.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FormationController.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeToEv.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDelete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DeleteEv.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/QUIZZview.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAjouterEvenement(ActionEvent event) {
        try {
            Parent ajouterEvenementParent = FXMLLoader.load(getClass().getResource("/AjouterEv.fxml"));
            Scene ajouterEvenementScene = new Scene(ajouterEvenementParent);

            // Obtenez la fenêtre (stage) et changez la scène
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ajouterEvenementScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Update(ActionEvent event) {
        try {
            Parent ajouterEvenementParent = FXMLLoader.load(getClass().getResource("/UpdateEv.fxml"));
            Scene ajouterEvenementScene = new Scene(ajouterEvenementParent);

            // Obtenez la fenêtre (stage) et changez la scène
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ajouterEvenementScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aller(ActionEvent event) {
        try {
            Parent rsparent = FXMLLoader.load(getClass().getResource("/WelcomeRs.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene rsscene = new Scene(rsparent);
            window.setScene(rsscene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAfficherEvenements(ActionEvent event) {
        try {
            Parent afficherEvParent = FXMLLoader.load(getClass().getResource("/AfficherEv.fxml"));
            Scene afficherEvScene = new Scene(afficherEvParent);

            // Récupérer le stage actuel
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(afficherEvScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToRole(ActionEvent actionEvent) {
    }
}


