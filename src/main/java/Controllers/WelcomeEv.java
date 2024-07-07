package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
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
    private void handleAjouterEvenement(ActionEvent event) {
        try {
            Parent ajouterEvenementParent = FXMLLoader.load(getClass().getResource("/AjouterEv.fxml"));
            Scene ajouterEvenementScene = new Scene(ajouterEvenementParent);

            // Obtenez la fenêtre (stage) et changez la scène
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ajouterEvenementScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAfficherEvenements(ActionEvent event) {
        try{
        Parent afficherEvParent = FXMLLoader.load(getClass().getResource("/AfficherEv.fxml"));
        Scene afficherEvScene = new Scene(afficherEvParent);

        // Récupérer le stage actuel
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherEvScene);
        window.show();
    } catch (IOException e) {
            e.printStackTrace();}}}




