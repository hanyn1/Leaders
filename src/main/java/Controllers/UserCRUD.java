package Controllers;

import Models.Role;
import Models.User;
import Services.RoleService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.MyConfig;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UserCRUD implements Initializable {

    Connection instance = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Button btnAjou;

    @FXML
    private Button btnModi;

    @FXML
    private Button btnSupp;

    @FXML
    private TextField textEmail;

    @FXML
    private TableColumn<User, String> colRole;

    @FXML
    private ComboBox<Role> role;

    @FXML
    private TextField textMotDePasse;

    @FXML
    private TextField textName;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableView<Role> table;

    @FXML
    private TableColumn<User, String> colMotdepasse;

    @FXML
    private TableColumn<User, String> colNom;

    @FXML
    private TableView<User> tableUser;
    private ObservableList<User> utilisateurs;
    private UserService userService;
    private RoleService roleService;
    private ObservableList<Role> roles;

    int id=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    public ObservableList<User> getUtilisateurs(){
        ObservableList<User> utilisateurs = FXCollections.observableArrayList();

        String query = "select * from utilisateurs";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setMotDePasse(rs.getString("motDePasse"));

                utilisateurs.add(user);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;

    }

    public void showUtilisateurs(){
        ObservableList<User> list = getUtilisateurs();
        tableUser.setItems(list);
        colID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colMotdepasse.setCellValueFactory(new PropertyValueFactory<User,String>("motDePasse"));

    }

    @FXML
    void ajouterUser() {
        User user = new User(textName.getText(),textEmail.getText(),textMotDePasse.getText());
        UserService userService = new UserService();
        try {
            userService.ajouter(user);
            showUtilisateurs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'utilisateur a été ajouté avec succés");
        alert.show();

    }
    @FXML
    void getData(MouseEvent event) {
        User user = tableUser.getSelectionModel().getSelectedItem();
        id = user.getId();
        textName.setText(user.getNom());
        textEmail.setText(user.getEmail());
        textMotDePasse.setText(user.getMotDePasse());
        btnAjou.setDisable(true);

    }

    @FXML
    void modifierUser() {
        String modifier ="UPDATE utilisateurs SET nom = ?, email = ?, motDePasse = ? WHERE id = ?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(modifier);
            st.setString(1,textName.getText());
            st.setString(2,textEmail.getText());
            st.setString(3,textMotDePasse.getText());
            st.setInt(4,id);
            st.executeUpdate();

            showUtilisateurs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'utilisateur a été modifié avec succés");
        alert.show();

    }

    @FXML
    void supprimerUser() {
        String supprimer = "delete from utilisateurs where id=?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(supprimer);
            st.setInt(1,id);
            st.executeUpdate();
            showUtilisateurs();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'utilisateur a été supprimé avec succés");
        alert.show();


    }

    private boolean validateInput(String username, String password, String email) {
        boolean valid = true;

        if (username == null || username.isEmpty()) {
            showAlert("Invalid Input", "Username cannot be empty");
            valid = false;
        }

        if (password == null || password.isEmpty() || password.length() < 6) {
            showAlert("Invalid Input", "Password must be at least 6 characters long");
            valid = false;
        }

        if (email == null || email.isEmpty() || !isValidEmail(email)) {
            showAlert("Invalid Input", "Email is not valid");
            valid = false;
        }

        return valid;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setupValidation() {
        textEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!isValidEmail(textEmail.getText())) {
                    textEmail.setStyle("-fx-border-color: red;");
                } else {
                    textEmail.setStyle("");
                }
            }
        });

        textMotDePasse.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (textMotDePasse.getText().length() < 6) {
                    textMotDePasse.setStyle("-fx-border-color: red;");
                } else {
                    textMotDePasse.setStyle("");
                }
            }
        });
    }






}
