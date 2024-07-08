package tests;

import Controllers.TemoignagesController;
import Services.ServiceTemoignage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.MyConfig;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize MyConfig
            MyConfig myConfig = MyConfig.getInstance();

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TemoignagesController.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            TemoignagesController temoignagesController = loader.getController();

            // Create and set the ServiceTemoignage
            ServiceTemoignage serviceTemoignage = new ServiceTemoignage(myConfig);
            temoignagesController.setServiceTemoignage(serviceTemoignage);

            // Set up the primary stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Temoignages Management Application");
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
