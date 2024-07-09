package Controllers;

import Models.Evenement;
import Models.RessourceEv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DatabaseHelper;

public class UpdateEv {

    @FXML
    private Button bb;

    @FXML
    private Button close;

    @FXML
    private TableColumn<Evenement, String> desccc;

    @FXML
    private TextField espace1;

    @FXML
    private TextField espace2;

    @FXML
    private TextField espace3;

    @FXML
    private TableColumn<Evenement, Integer> id;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TableView<Evenement> tableview;

    @FXML
    private TableColumn<Evenement, String> titre;

    @FXML
    private Label titre11;

    @FXML
    private Label titre12;

    @FXML
    private Label titre13;

    @FXML
    private Label tittttre;

    @FXML
    private Label username;

    @FXML
    void close(ActionEvent event) {
        // Ajoutez ici le code pour fermer la fenêtre ou le formulaire
        main_form.getScene().getWindow().hide();
    }

    @FXML
    void minimize(ActionEvent event) {
        // Obtenez la référence à la Stage depuis la scène principale
        Stage stage = (Stage) main_form.getScene().getWindow();

        // Minimiser la fenêtre
        stage.setIconified(true);
    }

    @FXML
    public void initialize() {
        // Configurez les cellules des colonnes avec les propriétés de RessourceEv
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        desccc.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Remplir la table avec les données de la base de données
        ObservableList<Evenement> list = DatabaseHelper.getEvenements();
        tableview.setItems(list);
    }

    @FXML
    void submitfn(ActionEvent event) {
        Evenement selectedE = tableview.getSelectionModel().getSelectedItem();
        if (selectedE != null) {
            selectedE.setTitre(espace2.getText());
            selectedE.setDescription(espace3.getText());

            // Mettre à jour dans la base de données via DatabaseHelper
            DatabaseHelper.updateEvenemets(selectedE);

            // Rafraîchir la TableView après la mise à jour
            tableview.refresh();
        }
    }

    @FXML
    void deleteRessourceEv(ActionEvent event) {
        Evenement selected = tableview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Supprimer la ressource de la base de données via DatabaseHelper
            DatabaseHelper.deleteEvenement(selected);

            // Rafraîchir la TableView après la suppression
            tableview.getItems().remove(selected);
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Evenement selectedRessource = tableview.getSelectionModel().getSelectedItem();
        if (selectedRessource != null) {
            espace3.setText(selectedRessource.getDescription());
            espace2.setText(selectedRessource.getTitre());
            // Pré-remplir d'autres champs si nécessaire
        }
    }
}
