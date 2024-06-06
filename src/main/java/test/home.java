package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.management.jfr.FlightRecorderMXBean;

import java.io.IOException;

public class home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../AjouterPersonne.fxml"));
        try{
            Parent root= loader.load();
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter personnes");
            primaryStage.show();
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
