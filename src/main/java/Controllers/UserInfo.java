package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Email;

    @FXML
    private TextField ID;

    @FXML
    private TextField MotDePasse;

    @FXML
    private TextField Nom;


    public void setEmail(String email) {
        this.Email.setText(email);
    }

    public void setID(String ID) {
        this.ID.setText(ID);
    }

    public void setMotDePasse(String motDePasse) {
        this.MotDePasse.setText(motDePasse);
    }

    public void setNom(String nom) {
        this.Nom.setText(nom);
    }

    @FXML
    void initialize() {  }

}
