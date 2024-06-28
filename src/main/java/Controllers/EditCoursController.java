package Controllers;

import Services.ServiceCours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.Cours;

import java.sql.SQLException;

public class EditCoursController {

    @FXML
    private TextField editTitleTF;

    @FXML
    private TextField editDescTF;

    @FXML
    private TextField editImageTF;

    @FXML
    private TextField editVideoTF;

    private Cours selectedCours;
    private ServiceCours serviceCours = new ServiceCours();
    public void setCours(Cours cours) {
        this.selectedCours = cours;
        editTitleTF.setText(cours.getTitre());
        editDescTF.setText(cours.getDescription());
        editImageTF.setText(cours.getImage());
        editVideoTF.setText(cours.getVideo());
    }

    @FXML
    public void saveChanges(ActionEvent event) {
        selectedCours.setTitre(editTitleTF.getText());
        selectedCours.setDescription(editDescTF.getText());
        selectedCours.setImage(editImageTF.getText());
        selectedCours.setVideo(editVideoTF.getText());

        try {
            serviceCours.update(selectedCours);
            System.out.println("Course updated successfully!");
            closeWindow();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    public void deleteCourse(ActionEvent event) {
        int idToDelete = selectedCours.getId();
        try {

            serviceCours.delete(idToDelete);
            System.out.println("Course deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }
    @FXML
    public void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) editTitleTF.getScene().getWindow();
        stage.close();
    }
}
