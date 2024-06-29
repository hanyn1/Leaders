package Controllers;

import Models.Formation;
import Services.ServiceFormation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FormationController {
    @FXML
    private TextField titreField;
    @FXML
    private Button textAdd;

    @FXML
    private Button textDelete;

    @FXML
    private TextField descriptionField;
    @FXML
    private TableView<Formation> formationTable;
    @FXML
    private TableColumn<Formation, Integer> idColumn;
    @FXML
    private TableColumn<Formation, String> titreColumn;
    @FXML
    private TableColumn<Formation, String> descriptionColumn;

    private ServiceFormation serviceFormation;

    public void setServiceFormation(ServiceFormation serviceFormation) {
        this.serviceFormation = serviceFormation;
        loadFormations();
    }

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        loadFormations();
    }

    private void loadFormations() {
        if (serviceFormation != null) {
            formationTable.setItems(FXCollections.observableArrayList(serviceFormation.getAllFormations()));
        }
    }

    @FXML
    private void handleAddFormation() {
        Formation formation = new Formation();
        formation.setTitre(titreField.getText());
        formation.setDescription(descriptionField.getText());
        serviceFormation.addFormation(formation);
        loadFormations();
    }

    @FXML
    private void handleDeleteFormation() {
        Formation selectedFormation = formationTable.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            serviceFormation.deleteFormation(selectedFormation.getId());
            loadFormations();
        }
    }
}
