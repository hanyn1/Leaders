package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomeRs {

        @FXML
        private Button AfficherRss;

        @FXML
        private Button AjouterRss;

        @FXML
        private Button MiseAJourRss;

        @FXML
        private Button SupprimerRss;

        @FXML
        private Label Titre;



        public void goToEvent(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeToEv.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        @FXML
        private void handleAddResourceButtonAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRs.fxml"));
                        Parent addRsParent = loader.load();
                        Scene addRsScene = new Scene(addRsParent);

                        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        window.setScene(addRsScene);
                        window.show();
                } catch (IOException e) {
                        e.printStackTrace();
                        // Handle any IO exceptions gracefully
                }
        }

        @FXML
        private void handleReadRs(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReadRs.fxml"));
                        Parent addRsParent = loader.load();
                        Scene addRsScene = new Scene(addRsParent);

                        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        window.setScene(addRsScene);
                        window.show();
                } catch (IOException e) {
                        e.printStackTrace();
                        // Handle any IO exceptions gracefully
                }
        }
        @FXML
        private void handleUpdate(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateRss.fxml"));
                        Parent addRsParent = loader.load();
                        Scene addRsScene = new Scene(addRsParent);

                        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        window.setScene(addRsScene);
                        window.show();
                } catch (IOException e) {
                        e.printStackTrace();
                        // Handle any IO exceptions gracefully
                }
        }


        @FXML
        private void handleDelete(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteRs.fxml"));
                        Parent addRsParent = loader.load();
                        Scene addRsScene = new Scene(addRsParent);

                        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        window.setScene(addRsScene);
                        window.show();
                } catch (IOException e) {
                        e.printStackTrace();
                        // Handle any IO exceptions gracefully
                }
        }
}