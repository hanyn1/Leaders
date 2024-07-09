package Controllers;

import Models.Evenement;
import Models.RessourceEv;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DatabaseHelper;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.Objects;

public class DeleteEv {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableView<Evenement> tableview;

    @FXML
    private TableColumn<Evenement, Integer> id;

    @FXML
    private TableColumn<Evenement, String> titre;

    @FXML
    private TableColumn<Evenement, String> description;

    @FXML
    private Label espace2; // Placeholder for titre

    @FXML
    private Label espace3; // Placeholder for description

    @FXML
    void initialize() {
        // Configurez les cellules des colonnes avec les propriétés de Evenement
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Remplir la table avec les données de la base de données
        ObservableList<Evenement> list = DatabaseHelper.getEvenements();
        tableview.setItems(list);
    }

    @FXML
    void deleteEv(ActionEvent event) {
        Evenement ev = tableview.getSelectionModel().getSelectedItem();
        if (ev != null) {
            // Supprimer dans la base de données via DatabaseHelper
            DatabaseHelper.deleteEvenement(ev);

            // Rafraîchir la TableView après la suppression
            rafraichirTable();
            clearFields(); // Effacer les champs de texte
        }
    }

    private void rafraichirTable() {
        ObservableList<Evenement> list = DatabaseHelper.getEvenements();
        tableview.setItems(list);
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Evenement selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            espace3.setText(selectedEvent.getDescription());
            espace2.setText(selectedEvent.getTitre());
            // Pré-remplir d'autres champs si nécessaire
        }
    }

    @FXML
    void close(ActionEvent event) {
        // Fermer la fenêtre ou le formulaire
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimize(ActionEvent event) {
        // Minimiser la fenêtre
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    private void clearFields() {
        espace2.setText("");
        espace3.setText("");
        // Effacer d'autres champs si nécessaire
    }

        public void goToRS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeRs.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    @FXML
    void removeCustomer(ActionEvent event) {
        int selectedID = tableview.getSelectionModel().getSelectedIndex();
        tableview.getItems().remove(selectedID);
    }

}

