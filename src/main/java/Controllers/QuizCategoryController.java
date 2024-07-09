package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class QuizCategoryController {

    @FXML
    private AnchorPane categoryAnchorPane;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void initialize() {
        // Add buttons for each category
        addButton("JavaScript - Les bases");
        addButton("HTML - Les bases");
        addButton("PHP - Les bases");
        addButton("CSS - Les bases");
        addButton("Angular - Les bases");
        addButton("Git - Les bases");
        addButton("Java - Les bases");
    }

    private void addButton(String categoryName) {
        Button button = new Button(categoryName);
        button.setStyle("-fx-background-color: #f0db4f; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setPrefWidth(250);
        button.setPrefHeight(50);
        button.setOnAction(event -> handleCategorySelection(categoryName));
        categoryAnchorPane.getChildren().add(button);
        AnchorPane.setTopAnchor(button, (double) (50 + categoryAnchorPane.getChildren().indexOf(button) * 70));
        AnchorPane.setLeftAnchor(button, 50.0);
    }

    private void handleCategorySelection(String category) {
        if (category != null) {
            System.out.println("Catégorie sélectionnée: " + category);
            showAlert("Catégorie sélectionnée", "Vous avez choisi : " + category);
            loadHomeQPage();
        } else {
            System.out.println("Aucune catégorie sélectionnée");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadHomeQPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeQ.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) categoryAnchorPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleJavaBase(ActionEvent actionEvent) {
    }

    public void handleGitBase(ActionEvent actionEvent) {
    }

    public void handleAngularBase(ActionEvent actionEvent) {
    }

    public void handleCSSBase(ActionEvent actionEvent) {
    }

    public void handlePHPBase(ActionEvent actionEvent) {
    }

    public void handleHTMLBase(ActionEvent actionEvent) {
    }

    public void handleJavaScriptBase(ActionEvent actionEvent) {
    }
    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

}