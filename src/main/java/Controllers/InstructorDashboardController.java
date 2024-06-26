package Controllers;


import Models.Cours;
import Services.ServiceCours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InstructorDashboardController implements Initializable {

    @FXML
    private Label contentLabel;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private VBox recentCoursesVBox;

    private void populateRecentCourses() {
        ServiceCours sc = new ServiceCours();
        List<Cours> courses = sc.getAll();
        if (courses != null) {
            for (Cours course : courses) {
                AnchorPane coursePane = createCoursePane(course);
                recentCoursesVBox.getChildren().add(coursePane);
            }
        }
    }

    // Create an AnchorPane representing a course entry
    private AnchorPane createCoursePane(Cours course) {
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(300, 60);

        Label titleLabel = new Label(course.getTitre());
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(10);

        Button viewButton = new Button("View Details");
        viewButton.setLayoutX(210);
        viewButton.setLayoutY(10);
        viewButton.setOnAction(event -> viewCourseDetails(course));

        pane.getChildren().addAll(titleLabel, viewButton);
        return pane;
    }

    private void viewCourseDetails(Cours course) {
        // Example: Display details in an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Course Details");
        alert.setHeaderText(course.getTitre());
        alert.setContentText("Description: " + course.getDescription() + "\n" +
                "Video Link: " + course.getVideo());

        alert.showAndWait();
    }
    @FXML
    public void goToHome(ActionEvent actionEvent) {
        contentLabel.setText("Home Content Goes Here");
    }
    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void goToProfile(ActionEvent actionEvent) {
        contentLabel.setText("Profile Content Goes Here");
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent actionEvent) {
    }


    public void handleAddCourse(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCours.fxml"));
            Parent root = loader.load();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateRecentCourses();

    }
}
