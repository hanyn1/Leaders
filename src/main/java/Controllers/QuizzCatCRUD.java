package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.QuizzCat;
import Services.QuizzCatService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.MyConfig;

public class QuizzCatCRUD implements Initializable {

    Connection instance = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button TEXTdelete;

    @FXML
    private TextField desFX;

    @FXML
    private TextField nomFX;

    @FXML
    private Button textADD;

    @FXML
    private Button textUPDATE;

    int id = 0;
    public ObservableList<QuizzCat> getCatégorie(){
        ObservableList<QuizzCat> quizzcategorie = FXCollections.observableArrayList();

        String query = "select * from quizzcategorie";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                QuizzCat quizzCat = new QuizzCat();
                quizzCat.setId(rs.getInt("id"));
                quizzCat.setNom(rs.getString("nom"));
                quizzCat.setDescription(rs.getString("description"));

                quizzcategorie.add(quizzCat);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzcategorie;

    }

    @FXML
    void ADD(ActionEvent event) {
        QuizzCat quizzCat = new QuizzCat(nomFX.getText(),desFX.getText());
        QuizzCatService quizzCatService = new QuizzCatService();
        try {
            quizzCatService.addQuizz(quizzCat);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La catégorie a été ajouté avec succés");
        alert.show();

    }

    @FXML
    void DELETE(ActionEvent event) {
        String delete = "delete from quizzcategorie where id=?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La catégorie a été supprimé avec succés");
        alert.show();

    }

    @FXML
    void UPDATE(ActionEvent event) {
        String updateQuizz ="UPDATE quizzcategorie SET nom = ?, description = ? WHERE id = ?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(updateQuizz);
            st.setString(1,nomFX.getText());
            st.setString(2,desFX.getText());
            st.setInt(3,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La catégorie a été modifié avec succés");
        alert.show();

    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


