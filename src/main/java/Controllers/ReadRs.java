package Controllers;

import Models.RessourceEv;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DatabaseHelper;

public class ReadRs {

    @FXML
    private TableColumn<RessourceEv, String> Title;

    @FXML
    private TableColumn<RessourceEv, String> date;

    @FXML
    private TableColumn<RessourceEv, String> desc;

    @FXML
    private TableColumn<RessourceEv, Integer> id;

    @FXML
    private TableView<RessourceEv> tableview;

    @FXML
    private Label titre;

    @FXML
    private TableColumn<RessourceEv, String> url;

    @FXML
    public void initialize() {
        // Configurez les cellules des colonnes avec les propriétés de RessourceEv
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateString")); // Adapter si nécessaire
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        url.setCellValueFactory(new PropertyValueFactory<>("url"));

        // Remplir la table avec les données de la base de données
        ObservableList<RessourceEv> list = DatabaseHelper.getRessourcesEv();
        tableview.setItems(list);
    }
}
