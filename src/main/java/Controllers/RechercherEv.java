package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.EvenementService;
import Models.Evenement;

import java.io.IOException;
import java.sql.SQLException;

public class RechercherEv {

    @FXML
    private Button BRcherche;

    @FXML
    private Label Titre;

    @FXML
    private TextField espacerecher;

    @FXML
    private Label titre1;

    private EvenementService evenementService = new EvenementService(); // Instancier le service

    @FXML
    public void initialize() {
        BRcherche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String idText = espacerecher.getText();
                if (idText != null && !idText.isEmpty()) {
                    int id = Integer.parseInt(idText);
                    try {
                        Evenement evenement = evenementService.rechercherParId(id);
                        if (evenement != null) {
                            afficherEvenement(evenement);
                        } else {
                            Titre.setText("Aucun événement trouvé avec l'ID : " + id);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Titre.setText("Erreur lors de la recherche de l'événement.");
                    }
                } else {
                    Titre.setText("Veuillez entrer un ID valide");
                }
            }
        });
    }

    private void afficherEvenement(Evenement evenement) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherMonEv.fxml"));
        Parent root = loader.load();

        // Récupérer le contrôleur de la nouvelle vue
        AfficherMonEv afficherMonEvController = loader.getController();
        afficherMonEvController.setEvenement(evenement);

        Stage stage = (Stage) BRcherche.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
