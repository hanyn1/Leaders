package Controllers;

import Models.Cours;
import Services.CertificateGenerator;
import Services.EmailService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
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

    @FXML
    private TextField emailTextField;

    private Cours currentCourse;

    public void initialize() {
        // Default initialization, if needed
    }

    public void initialize(Cours course) {
        this.currentCourse = course;

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
        if (mediaPlayer != null && (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED || mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED)) {
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
    void generateAndSendCertificate() {
        String recipientEmail = emailTextField.getText();
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            // Show an error message
            return;
        }

        // Generate the certificate
        CertificateGenerator generator = new CertificateGenerator();
        String certificatePath = generator.generateCertificate("molk saouabi",currentCourse.getTitre(), LocalDate.now());

        // Send the certificate via email
        EmailService emailService = new EmailService();
        emailService.sendCertificate(recipientEmail, "Your Course Certificate", "Congratulations! Please find your certificate attached.", certificatePath);
        // Show success message
    }

    public void switchForm(ActionEvent event) {
    }

    public void goToCoursesList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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
}
