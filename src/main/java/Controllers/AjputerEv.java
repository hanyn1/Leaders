package Controllers;

import Models.Evenement;
import Services.EvenementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AjputerEv {
    @FXML
    private TextField descTF;

    @FXML
    private TextField titreTF;

    private EvenementService evenementService;

    @FXML
    public void initialize() {
        evenementService = new EvenementService(); // Initialiser le service
    }

    public void AjouterEventment(ActionEvent actionEvent) throws SQLException {
        String titre = titreTF.getText();
        String desc = descTF.getText();

        // Vérifiez que les champs ne sont pas vides
        if (titre.isEmpty() || desc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        Evenement evenement = new Evenement(titre, desc);
        {
            evenementService.ajouter(evenement);
            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Événement ajouté avec succès !");
            alert.showAndWait();
        }
    }

    public void close(ActionEvent actionEvent) {
        // Ajoutez le code pour fermer la fenêtre
    }

    public void minimize(ActionEvent actionEvent) {
        // Ajoutez le code pour minimiser la fenêtre
    }

    public void switchForm(ActionEvent actionEvent) {
        // Ajoutez le code pour changer de formulaire
    }

    public void goToCoursesList(ActionEvent actionEvent) {
        // Ajoutez le code pour aller à la liste des cours
    }
}

