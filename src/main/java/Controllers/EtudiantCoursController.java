package Controllers;

import Models.Cours;
import Models.User;
import Services.CertificateGenerator;
import Services.EmailService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private User user;
    private String userName = "Molk Saouabi";
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
    void generateAndSendCertificate() {
        String recipientEmail = emailTextField.getText();
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Recipient Email Required", "Please enter a recipient email.");
            return;
        }

        // Validate email format
        if (!isValidEmail(recipientEmail)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid Email Format", "Please enter a valid email address.");
            return;
        }

        // Generate the certificate
        CertificateGenerator generator = new CertificateGenerator();
        String certificatePath = generator.generateCertificate(userName, currentCourse.getTitre(), LocalDate.now());

        // Send the certificate via email
        EmailService emailService = new EmailService();
        try {
            emailService.sendCertificate(recipientEmail, "Your Course Certificate", "Congratulations! Please find your certificate attached.", certificatePath);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Certificate Sent", "The certificate has been successfully sent to " + recipientEmail);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to Send Certificate", "An error occurred while sending the certificate. Please try again later.");
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        // Email validation regex pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void goToFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FormationController.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantEvents.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/HomeQ.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
