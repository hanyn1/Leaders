package Controllers;

import Models.Cours;
import Services.ServiceCours;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


import java.awt.event.ActionEvent;

public class CoursControllers {

    @FXML
    private ImageView courseImageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    @FXML
    void pauseVideo(ActionEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void playVideo(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stopVideo(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }



    public void initialize() {

        Cours c = new Cours();

        // Load course details based on courseId
        loadCourseDetails(c.getId());
    }

    public void loadCourseDetails(int id) {
        // Retrieve course details from the service layer based on courseId
        ServiceCours sc = new ServiceCours();
        Cours course = sc.getCoursById(id);
        if (course != null) {
            // Populate UI elements with course details
            titleLabel.setText(course.getTitre());
            descriptionLabel.setText(course.getDescription());

            // Load image (if available)
            if (course.getImage() != null && !course.getImage().isEmpty()) {
                courseImageView.setImage(new Image(course.getImage()));
            }
        } else {
            // Handle case where course with given ID is not found
            titleLabel.setText("Course Not Found");
            descriptionLabel.setText("The course with ID " + id + " does not exist.");
            courseImageView.setImage(null); // Clear image if course is not found
        }
    }

}

