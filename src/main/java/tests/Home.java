package tests;

import Controllers.FormationController;
import Services.ServiceFormation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormationController.fxml"));
        Scene scene = new Scene(loader.load());

        FormationController controller = loader.getController();
        controller.setServiceFormation(new ServiceFormation()); // Set the service

        primaryStage.setScene(scene);
        primaryStage.setTitle("Formation Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
