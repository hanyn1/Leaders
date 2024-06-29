package Controllers;

import Models.Cours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EtudiantCoursController {
    @FXML
    private ImageView courseImageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane main_form;

    public void initialize(Cours course) {
        // Populate UI elements with course details
        titleLabel.setText(course.getTitre());
        descriptionLabel.setText(course.getDescription());

        // Load image (if available)
        if (course.getImage() != null && !course.getImage().isEmpty()) {
            courseImageView.setImage(new Image(course.getImage()));
        }

        // Load video (if available)
        if (course.getVideo() != null && !course.getVideo().isEmpty()) {
            Media media = new Media(course.getVideo());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setVisible(true); // Show MediaView
        } else {
            mediaView.setVisible(false); // Hide MediaView if no video available
        }
    }

    @FXML
    void pauseVideo() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void playVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
