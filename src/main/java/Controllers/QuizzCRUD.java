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
            String titre = titreField.getText();
            String description = descriptionField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String rightAnswer = getSelectedRightAnswer();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Quizz quizz = new Quizz(titre, description, option1, option2, option3, rightAnswer, timestamp);
            serviceQuizz.addQuizz(quizz);
            quizzList.add(quizz);
        } catch (Exception e) {
            e.printStackTrace();
        }
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