package Controllers;

import Models.Quizz;
import Services.ServiceQuizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
public class QuizzCRUD implements Initializable {

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
    private TableView<Quizz> quizzTableView;
    @FXML
    private TableColumn<Quizz, String> titreColumn;
    @FXML
    private TableColumn<Quizz, String> descriptionColumn;

    private ServiceQuizz serviceQuizz;
    private ObservableList<Quizz> quizzList;

    @FXML
    private AnchorPane main_form;

    public QuizzCRUD() {
        serviceQuizz = new ServiceQuizz();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizzList = FXCollections.observableArrayList();
        quizzTableView.setItems(quizzList);

        // Set up table columns
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadQuizzes();
    }

    private void loadQuizzes() {
        try {
            List<Quizz> quizzes = serviceQuizz.getAllQuizzes();
            quizzList.setAll(quizzes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addQuizz(ActionEvent event) {
        try {
            // Validation des champs de saisie
            String titre = titreField.getText();
            String description = descriptionField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String rightAnswer = getSelectedRightAnswer();

            if (titre.isEmpty() || description.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || rightAnswer.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur de validation", "Tous les champs doivent être remplis et une réponse correcte doit être sélectionnée.");
                return;
            }

            // Validation des caractères pour les champs title et description
            if (!titre.matches("[\\p{L} ]+") || !description.matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Erreur de validation", "Les champs titre et description ne doivent contenir que des lettres et des espaces.");
                return;
            }

            // Création du quiz
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Quizz quizz = new Quizz(titre, description, option1, option2, option3, rightAnswer, timestamp);

            // Ajout du quiz à la base de données et à la liste observable
            serviceQuizz.addQuizz(quizz);
            quizzList.add(quizz);

            // Effacement des champs de saisie après l'ajout du quiz
            clearFields();

            // Affichage d'un message de succès
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Quiz ajouté avec succès !");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'ajout du quiz : " + e.getMessage());
            e.printStackTrace();
        }
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    public void updateQuizz(ActionEvent event) {
        Quizz selectedQuizz = quizzTableView.getSelectionModel().getSelectedItem();
        if (selectedQuizz != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditQuizz.fxml"));
                Parent root = loader.load();
                EditQuizzController controller = loader.getController();
                controller.setQuizz(selectedQuizz);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
                quizzTableView.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteQuizz(ActionEvent event) {
        Quizz selectedQuizz = quizzTableView.getSelectionModel().getSelectedItem();
        if (selectedQuizz != null) {
            try {
                serviceQuizz.deleteQuizz(selectedQuizz.getId());
                quizzList.remove(selectedQuizz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getSelectedRightAnswer() {
        if (rightOption1.isSelected()) {
            return option1Field.getText();
        } else if (rightOption2.isSelected()) {
            return option2Field.getText();
        } else if (rightOption3.isSelected()) {
            return option3Field.getText();
        }
        return "";
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent actionEvent) {
    }

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToUsers(ActionEvent event) {
    }

    public void goToArticles(ActionEvent event) {
    }

    public void goToFormation(ActionEvent event) {
    }
}