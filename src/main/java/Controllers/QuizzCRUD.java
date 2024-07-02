package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;

public class QuizzCRUD {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Option1;

    @FXML
    private Button artiles_btn;

    @FXML
    private Button avaialbeFD_btn;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button event_btn;

    @FXML
    private Button formation_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TextField option2;

    @FXML
    private TextField option3;

    @FXML
    private Button order_btn;

    @FXML
    private Button quizz_btn;

    @FXML
    private Button submitQuiz;

    @FXML
    private Button textAdd;

    @FXML
    private TextField textDescription;

    @FXML
    private TextField textTitre;

    @FXML
    private Label username;

    @FXML
    private Button users_btn;
    private ActionEvent event;

    @FXML
    void add(ActionEvent event) {
        String titre = textTitre.getText();
        String description = textDescription.getText();

        if (validateTextInput(titre, description)) {
            // Ajouter le titre et la description dans votre logique d'application
            showAlert(Alert.AlertType.INFORMATION, "Success", "Add A new Question");
            clearFields(); // Réinitialise les champs de texte après ajout
        }
    }
    private void clearFields() {
        textTitre.clear();
        textDescription.clear();
        Option1.clear();
        option2.clear();
        option3.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void goToCoursesList(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void submitQuiz(ActionEvent event) {
            // Code pour soumettre le quiz
            String opt1 =Option1.getText();
            String opt2 =option2 .getText();
            String opt3 = option3.getText();

            // Logique de soumission du quiz
            if (!opt1.isEmpty() && !opt2.isEmpty() && !opt3.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Quiz submitted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "All options must be filled!");
            }
        }

    @FXML
    void switchForm(ActionEvent event) {

    }

    private void radioButtonSetup() {

}

    @FXML
    void initialize() {
        radioButtonSetup();
    }
    private boolean validateTextInput(String titre, String description) {
        if (titre.isEmpty() || description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Title and Description cannot be empty!");
            return false;
        }
        if (!isAlpha(titre)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Title must contain only letters!");
            return false;
        }
        return true;
    }

    private boolean validateQuizInput(String opt1, String opt2, String opt3) {
        if (opt1.isEmpty() || opt2.isEmpty() || opt3.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All quiz options must be filled!");
            return false;
        }
        if (!isNumeric(opt1) || !isNumeric(opt2) || !isNumeric(opt3)) {
            showAlert(Alert.AlertType.ERROR, "Error", "All quiz options must contain only numbers!");
            return false;
        }
        return true;
    }

    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}


