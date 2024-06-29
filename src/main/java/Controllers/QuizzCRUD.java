package Controllers;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import Models.Quizz;
import Models.User;
import Services.QuizzCatService;
import Services.ServiceQuizz;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.MyConfig;

public class QuizzCRUD implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button textAdd;

    @FXML
    private DatePicker textDate;

    @FXML
    private Button textDelete;

    @FXML
    private TextField textDescription;

    @FXML
    private TextField textTitre;

    @FXML
    private Button textUpdate;

    Connection instance = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int id=0;

    public ObservableList<Quizz> getQuizz(){
        ObservableList<Quizz> quizzs = FXCollections.observableArrayList();

        String query = "select * from quizzs";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Quizz quizz = new Quizz();
                quizz.setId(rs.getInt("id"));
                quizz.setTitre(rs.getString("titre"));
                quizz.setDescription(rs.getString("description"));
                quizz.setDate(rs.getDate("date"));


                quizzs.add(quizz);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzs;

    }




    @FXML
    void add(ActionEvent event) {
        Quizz q = new Quizz(textTitre.getText(),textDescription.getText(),textDate.getValue());
        ServiceQuizz serviceQuizz = new ServiceQuizz();
        try {
            serviceQuizz.addQuizz(q);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le descripton a été ajouté avec succés");
        alert.show();

    }

    @FXML
    void delete(ActionEvent event) {
        String deleteQuizz = "delete from quizzs where id=?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(deleteQuizz);
            st.setInt(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le quizz a été supprimé avec succés");
        alert.show();

    }

    @FXML
    void update(ActionEvent event) {
        String updateQuizz ="UPDATE utilisateurs SET titre = ?, description = ?, date = ? WHERE id = ?";
        instance = MyConfig.getInstance().getConnection();
        try {
            st = instance.prepareStatement(updateQuizz);
            st.setString(1,textTitre.getText());
            st.setString(2,textDescription.getText());
            st.setDate(3, Date.valueOf(textDate.getValue()));
            st.setInt(4,id);
            st.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'utilisateur a été modifié avec succés");
        alert.show();
    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}