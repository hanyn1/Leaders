package Controllers;

import Models.Quizz;
import Services.ServiceQuizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class EditQuizzController {

    @FXML
    private TextField titreField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField option1Field;
    @FXML
    private TextField option2Field;
    @FXML
    private TextField option3Field;
    @FXML
    private RadioButton rightOption1;
    @FXML
    private RadioButton rightOption2;
    @FXML
    private RadioButton rightOption3;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Quizz> tableView;
    @FXML
    private TableColumn<Quizz, String> colTitre;
    @FXML
    private TableColumn<Quizz, String> colDescription;

    private ServiceQuizz serviceQuizz;
    private ObservableList<Quizz> quizList;
    private Stage stage;

    public EditQuizzController() {
        serviceQuizz = new ServiceQuizz();
    }

    public static EditQuizzController createEditQuizzController() {
        return new EditQuizzController();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setQuizz(Quizz quizz) {
        initializeFields(quizz);
    }

    @FXML
    private void initializeFields(Quizz quizz) {
        titreField.setText(quizz.getTitre());
        descriptionField.setText(quizz.getDescription());
        option1Field.setText(quizz.getOption1());
        option2Field.setText(quizz.getOption2());
        option3Field.setText(quizz.getOption3());

        // Assume right answer is stored in some manner, e.g., in the description
        if (quizz.getDescription().equals("Option 1")) {
            rightOption1.setSelected(true);
        } else if (quizz.getDescription().equals("Option 2")) {
            rightOption2.setSelected(true);
        } else if (quizz.getDescription().equals("Option 3")) {
            rightOption3.setSelected(true);
        }
    }

    @FXML
    private void saveQuizz() throws SQLException {
        String titre = titreField.getText();
        String description = descriptionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();

        // Set the right answer
        if (rightOption1.isSelected()) {
            description = "Option 1";
        } else if (rightOption2.isSelected()) {
            description = "Option 2";
        } else if (rightOption3.isSelected()) {
            description = "Option 3";
        }

        Quizz quizz = new Quizz(titre, description, option1, option2, option3);
        serviceQuizz.addQuizz(quizz);

        // Clear the text fields after saving
        clearFields();

        // Update the TableView
        updateTableView();
    }

    @FXML
    private void cancelEdit() {
        // Simply close the window without saving
        stage.close();
    }

    @FXML
    private void updateTableView() throws SQLException {
        List<Quizz> quizzes = serviceQuizz.getAllQuizzes();
        quizList = FXCollections.observableArrayList(quizzes);
        tableView.setItems(quizList);
    }

    private void clearFields() {
        titreField.clear();
        descriptionField.clear();
        option1Field.clear();
        option2Field.clear();
        option3Field.clear();
        rightOption1.setSelected(false);
        rightOption2.setSelected(false);
        rightOption3.setSelected(false);
    }
}