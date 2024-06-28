package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminDashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchForm(ActionEvent actionEvent) {
    }

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) {
    }

    public void minimize(ActionEvent actionEvent) {
    }

    public void addCours(ActionEvent actionEvent) {
    }

    public void uploadImage(ActionEvent actionEvent) {
    }

    public void uploadVideo(ActionEvent actionEvent) {
    }

    public void deleteCours(ActionEvent actionEvent) {
    }

    public void updateCours(ActionEvent actionEvent) {
    }
}
