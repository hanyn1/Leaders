package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SendEmail {

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
    private Stage stage;
    private Scene scene;
    private Parent root;

    int randomCode;

    @FXML
    void Enter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EnterNewPassword.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Send(ActionEvent event) {
        Random rand = new Random();
        randomCode=rand.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "tsnemailsndr@gmail.com";
        String pass = "vQ#Tgez@=KRFM2E4";
        String to =textSend.getText();
        String sbject = "Reseting Code";
        String message ="Your reset code is"+randomCode;
        boolean sessionDebug = false;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","host");
        pros.put("mail.smtp.port","587");
        pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.starttls.required","true");












    }

    @FXML
    void initialize() {

    }

}
