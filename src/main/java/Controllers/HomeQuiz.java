package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HomeQuiz {

    @FXML
    private Button playquizbtn;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void initialize() {
        playquizbtn.setOnAction(this::handlePlayQuizButtonAction);
    }

    @FXML
    private void handlePlayQuizButtonAction(ActionEvent event) {
        try {
            Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            thisStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void goToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UserCRUD.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void goToFormation(ActionEvent event) throws IOException {

    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantEvents.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomeQ.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
