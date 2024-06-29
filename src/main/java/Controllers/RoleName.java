package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RoleName {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button TextWolcome;

    @FXML
    private ComboBox<?> textRole;

    @FXML
    void Role(ActionEvent event) {

    }

    @FXML
    void Welcome(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert TextWolcome != null : "fx:id=\"TextWolcome\" was not injected: check your FXML file 'RoleName.fxml'.";
        assert textRole != null : "fx:id=\"textRole\" was not injected: check your FXML file 'RoleName.fxml'.";

    }

}