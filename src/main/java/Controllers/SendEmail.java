package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SendEmail implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textCode;

    @FXML
    private Button textEnter;

    @FXML
    private TextField textSend;

    @FXML
    private Button textSendEmail;

    private String verificationCode;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String email;


    int randomCode;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @FXML
    void Enter(ActionEvent event) throws IOException {
        String code = textCode.getText();
        if (code.equals(verificationCode)) {
            showAlert(Alert.AlertType.CONFIRMATION, "Verification Successful", "The code is correct.");

            // Navigate to reset password scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnterNewPassword.fxml"));
            root = loader.load();
            EnterNewPassword resetPasswordController = loader.getController();
            resetPasswordController.setEmail(email);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "Verification Failed", "The code is incorrect. Please try again.");
        }

    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    @FXML
    void Send(ActionEvent event) {
        String email = textSend.getText();


        if (!validateEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        verificationCode = generateVerificationCode();

        if (sendVerificationEmail(email, verificationCode)) {
            showAlert(Alert.AlertType.CONFIRMATION, "Email Sent", "A verification code has been sent to your email.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Email Error", "Failed to send the email. Please try again.");
        }

    }

    private boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    private boolean sendVerificationEmail(String to, String code) {
        final String username = "samaalichayma99@gmail.com";
        final String password = "bfdn mhfl xmmy ofre";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress("samaalichayma99@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Password Reset Verification Code");
            message.setText("Your verification code is: " + code);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
