package Controllers;

import Models.RessourceEv;
import Services.RessourceEvService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddRs {

    @FXML
    private TextField descTF;

    @FXML
    private Button Button;

    @FXML
    private AnchorPane ajouterCoursPane;

    @FXML
    private Button artiles_btn;

    @FXML
    private Button avaialbeFD_btn;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_btn;

    @FXML
    private TextField date;

    @FXML
    private Button event_btn;

    @FXML
    private Button formation_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button order_btn;

    @FXML
    private Button quizz_btn;

    @FXML
    private TextField text1;

    @FXML
    private TextField text3;

    @FXML
    private TextField url;

    @FXML
    private Label texttt;

    @FXML
    private Label title;

    @FXML
    private Button users_btn;

    private RessourceEvService ressourceEvService;

    @FXML
    public void initialize() {
        ressourceEvService = new RessourceEvService();
        System.out.println("Service initialisé");
    }

    @FXML
    public void AjouterRs(ActionEvent actionEvent) throws SQLException, ParseException {
        System.out.println("AjouterRs appelé");
        String titre = text1.getText();
        String description = descTF.getText();
        String urlString = url.getText();
        String dateString = date.getText();

        if (titre.isEmpty() || description.isEmpty() || urlString.isEmpty() || dateString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        System.out.println("Tous les champs sont remplis");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(dateString + " 00:00:00");
            Timestamp timestamp = new Timestamp(parsedDate.getTime());

            RessourceEv rs = new RessourceEv(titre, description, urlString, timestamp);

            ressourceEvService.ajouter(rs);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Ressource ajoutée avec succès !");
            alert.showAndWait();
        } catch (ParseException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Format de date incorrect.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout de la ressource.");
            alert.showAndWait();
        }
    }

    @FXML
    public void close(ActionEvent actionEvent) {
        // Ajouter le code pour fermer la fenêtre
    }

    @FXML
    public void minimize(ActionEvent actionEvent) {
        // Ajouter le code pour minimiser la fenêtre
    }

    @FXML
    public void switchForm(ActionEvent actionEvent) {
        // Ajouter le code pour changer de formulaire
    }

    @FXML
    public void goToCoursesList(ActionEvent actionEvent) {
        // Ajouter le code pour aller à la liste des cours
    }

    public void AjouterEventment(ActionEvent actionEvent) {
        // Ajouter le code pour ajouter un événement
    }

    public void goToRS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeRs.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
