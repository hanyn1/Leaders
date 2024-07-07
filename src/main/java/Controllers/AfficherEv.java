package Controllers;

import Models.Evenement;
import utils.DatabaseHelper; // Importer DatabaseHelper depuis le package utils
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherEv implements Initializable {

    @FXML
    private TableColumn<Evenement, String> Title;

    @FXML
    private TableColumn<Evenement, String> desc;

    @FXML
    private TableColumn<Evenement, Long> id;

    @FXML
    private TableView<Evenement> tableview;

    @FXML
    private Label titre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Utiliser les noms corrects des propriétés
        id.setCellValueFactory(new PropertyValueFactory<Evenement, Long>("id"));
        desc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        Title.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titre"));

        // Remplir la table avec les données de la base de données
        ObservableList<Evenement> list = DatabaseHelper.getEvenements();
        tableview.setItems(list);
    }
}
