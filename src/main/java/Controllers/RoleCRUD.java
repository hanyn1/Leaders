package Controllers;

import Models.Role;
import Models.User;
import Services.RoleService;
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
import javafx.stage.Stage;
import utils.MyConfig;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static utils.MyConfig.*;

public class RoleCRUD implements Initializable {
    Connection instance = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Button btnADD;

    @FXML
    private Button btnMOD;

    @FXML
    private Button btnSUPP;

    @FXML
    private Button textInscrire;

    @FXML
    private TableColumn<Role, Integer> columnID;

    @FXML
    private TableColumn<Role, String> columnNAME;

    @FXML
    private TextField roleInput;

    @FXML
    private TableView<Role> table;

    private RoleService roleService;
    private ObservableList<Role> roles;

    private Stage stage;
    private Scene scene;
    private Parent root;
    int id=0;

    @FXML
    void initialize() {

    }

    public ObservableList<Role> getRoles(){
        ObservableList<Role> roles = FXCollections.observableArrayList();

        String query = "select * from roles";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));


                roles.add(role);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;

    }

    public void showRole(){
        ObservableList<Role> list = getRoles();
        table.setItems(list);
        columnID.setCellValueFactory(new PropertyValueFactory<Role,Integer>("id"));
        columnNAME.setCellValueFactory(new PropertyValueFactory<Role,String>("name"));

    }

    @FXML
    void ajouterRole(ActionEvent event) {

        String roleName = roleInput.getText().trim();

        if (roleName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez entrer un nom de rôle.");
            alert.show();
            return;
        }

        Role role = new Role(roleInput.getText());
        RoleService roleService = new RoleService();
        try {
            roleService.ajouter(role);
            showRole();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le role a été ajouté avec succés");
        alert.show();


    }
    @FXML
    void getData(MouseEvent event) {
        Role role = table.getSelectionModel().getSelectedItem();
        id = role.getId();
        roleInput.setText(role.getName());

        btnADD.setDisable(true);

    }


    @FXML
    void modifierRole(ActionEvent event) {
        String roleName = roleInput.getText().trim();

        if (roleName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez entrer un nom de rôle.");
            alert.show();
            return;
        }

        String modifier ="UPDATE roles SET name = ? WHERE id = ?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(modifier);
            st.setString(1,roleInput.getText());
            st.setInt(2,id);
            st.executeUpdate();

            showRole();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le role a été modifié avec succés");
        alert.show();

    }

    @FXML
    void supprimerRole(ActionEvent event) {
        String supprimer = "delete from roles where id=?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(supprimer);
            st.setInt(1,id);
            st.executeUpdate();
            showRole();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le role a été supprimé avec succés");
        alert.show();

    }

    @FXML
    void Inscrire(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UserCRUD.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}