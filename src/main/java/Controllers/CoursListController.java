package Controllers;

import Models.Cours;
import Services.ServiceCours;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CoursListController {

    @FXML
    private VBox coursesVBox;

    private ServiceCours serviceCours;

    @FXML
    public void initialize() {
        serviceCours = new ServiceCours();
        List<Cours> courses = serviceCours.getAll();
        displayCourses(courses);
    }

    private void displayCourses(List<Cours> courses) {
        coursesVBox.getChildren().clear(); // Clear existing content
        for (Cours course : courses) {
            HBox card = createCourseCard(course);
            card.setOnMouseClicked(event -> handleCardClick(course)); // Set click handler
            coursesVBox.getChildren().add(card);
        }
    }

    private HBox createCourseCard(Cours course) {
        HBox card = new HBox(10);
        card.setStyle("-fx-padding: 10px;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");
        card.setPrefWidth(400);

        ImageView imageView = new ImageView();
        if (course.getImage() != null && !course.getImage().isEmpty()) {
            try {
                imageView = new ImageView(new Image(course.getImage()));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error loading image: " + course.getImage());
            }
        } else {
            System.out.println("No image available for course: " + course.getTitre()); // Debugging statement
        }

        VBox detailsVBox = new VBox(5);
        Label titleLabel = new Label(course.getTitre());
        Label descriptionLabel = new Label(course.getDescription());
        descriptionLabel.setWrapText(true);

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel);

        card.getChildren().addAll(imageView, detailsVBox);

        return card;
    }

    private void handleCardClick(Cours course) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cours.fxml"));
            Parent root = loader.load();

            CoursControllers controller = loader.getController();
            controller.initialize(course);

            coursesVBox.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCours.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) coursesVBox.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
