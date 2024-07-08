package tests;

import Controllers.FormationController;
import Services.ServiceFormation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormationController.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            FormationController formationController = loader.getController();

            // Create and set the ServiceFormation
            ServiceFormation serviceFormation = new ServiceFormation(); // Initialize with your necessary configuration
            formationController.setServiceFormation(serviceFormation);

            // Set up the primary stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Formation Management Application");
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
