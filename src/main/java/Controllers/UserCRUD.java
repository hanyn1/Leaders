package Controllers;

import Models.Role;
import Models.User;
import Services.RoleService;
import Services.UserService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyConfig;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
    private PasswordField textMotDePasse;

    @FXML
    private CheckBox textShow;

    @FXML
    private TextField textName;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private Label labelShow;

    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private TableColumn<User, String> colMotdepasse;

    @FXML
    private TableColumn<User, String> colNom;

    @FXML
    private TableView<User> tableUser1;
    private ObservableList<User> utilisateurs;
    private UserService userService;
    private RoleService roleService;
    private ObservableList<Role> roles;

    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;

    int id = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation de la connexion à la base de données
        instance = MyConfig.getInstance().getConnection();

        // Afficher les utilisateurs dans la table
        showUtilisateurs();
    }

    @FXML
    void ShowPassword(ActionEvent event) {
        if (textShow.isSelected()) {
            labelShow.setVisible(true);
            labelShow.textProperty().bind(Bindings.concat(textMotDePasse.getText()));
            textShow.setText("Hide");
        } else {
            labelShow.setVisible(false);
            textShow.setText("show");
        }
    }

    public ObservableList<User> getUtilisateurs() {
        ObservableList<User> utilisateurs = FXCollections.observableArrayList();

        String query = "SELECT * FROM utilisateurs";
        try {
            st = instance.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
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

    public void showUtilisateurs() {
        ObservableList<User> list = getUtilisateurs();
        tableUser1.setItems(list);
        colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colMotdepasse.setCellValueFactory(new PropertyValueFactory<User, String>("motDePasse"));
    }

    @FXML
    void ajouterUser() {
        String name = textName.getText().trim();
        String email = textEmail.getText().trim();
        String password = textMotDePasse.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
            alert.show();
            return;
        }

        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Format d'email incorrect.");
            alert.show();
            return;
        }

        if (password.length() < 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Le mot de passe doit comporter au moins 6 caractères.");
            alert.show();
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(name, email, hashedPassword);
        UserService userService = new UserService();
        try {
            userService.ajouter(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'utilisateur a été ajouté avec succès.");
            alert.show();
            showUtilisateurs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        User user = tableUser1.getSelectionModel().getSelectedItem();
        if (user != null) {
            id = user.getId();
            textName.setText(user.getNom());
            textEmail.setText(user.getEmail());
            textMotDePasse.setText(user.getMotDePasse());
            btnAjou.setDisable(true);
        }
    }

    @FXML
    void modifierUser() {
        String name = textName.getText().trim();
        String email = textEmail.getText().trim();
        String password = textMotDePasse.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
            alert.show();
            return;
        }

        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Format d'email incorrect.");
            alert.show();
            return;
        }

        if (password.length() < 6) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Le mot de passe doit comporter au moins 6 caractères.");
            alert.show();
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String modifier = "UPDATE utilisateurs SET nom = ?, email = ?, motDePasse = ? WHERE id = ?";
        try {
            st = instance.prepareStatement(modifier);
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, hashedPassword);
            st.setInt(4, id);
            st.executeUpdate();

            showUtilisateurs();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'utilisateur a été modifié avec succès.");
            alert.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supprimerUser() {
        String supprimer = "DELETE FROM utilisateurs WHERE id = ?";
        try {
            st = instance.prepareStatement(supprimer);
            st.setInt(1, id);
            st.executeUpdate();
            showUtilisateurs();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'utilisateur a été supprimé avec succès.");
            alert.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        switchScene(event, "/AjouterCoursAdmin.fxml");
    }

    @FXML
    public void goToUsers(ActionEvent event) throws IOException {
        switchScene(event, "/RoleCRUD.fxml");
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageArticle.fxml")));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeToEv.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/QUIZZview.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToRole(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RoleCRUD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}