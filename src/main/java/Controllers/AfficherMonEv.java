package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Models.Evenement;

public class AfficherMonEv {

    @FXML
    private TableColumn<Evenement, String> DescriptionEv;

    @FXML
    private TableColumn<Evenement, String> TitreEv;

    @FXML
    private TableView<Evenement> table;

    @FXML
    private Label titre;

    public void setEvenement(Evenement evenement) {
        titre.setText(evenement.getTitre());

        // Assurez-vous que vos colonnes sont correctement configurées
        TitreEv.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        DescriptionEv.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        // Ajoutez l'événement à la table s'il n'est pas déjà présent
        if (!table.getItems().contains(evenement)) {
            table.getItems().add(evenement);
        }
    }
}