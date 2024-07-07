package Controllers;

import Models.Formation;
import Services.FormationService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FormationController {

    @FXML
    private TableColumn<Formation, String> descriptionColumn;

    @FXML
    private TextField descriptionField;

    @FXML
    private TableView<Formation> formationTable;

    @FXML
    private TableColumn<Formation, Number> idColumn;

    @FXML
    private Button textAdd;

    @FXML
    private Button textDelete;

    @FXML
    private TableColumn<Formation, String> titreColumn;

    @FXML
    private TextField titreField;

    private FormationService formationService;

    public void setFormationService(FormationService formationService) {
        this.formationService = formationService;
        loadFormations(); // Ensure to load formations after setting the service
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        loadFormations();
    }

    private void loadFormations() {
        if (formationService != null) {
            formationTable.setItems(FXCollections.observableArrayList(formationService.getAllFormations()));
        }
    }

    @FXML
    private void handleAddFormation() {
        if (formationService == null) {
            System.err.println("FormationService is not initialized.");
            return; // Exit method if service is null
        }

        Formation formation = new Formation(titreField.getText(), descriptionField.getText());
        formationService.addFormation(formation);
        loadFormations();
        clearFields();
    }


    @FXML
    private void handleDeleteFormation() {
        Formation selectedFormation = formationTable.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            formationService.deleteFormation(selectedFormation.getId());
            loadFormations();
        }
    }

    private void clearFields() {
        titreField.clear();
        descriptionField.clear();
    }
}
