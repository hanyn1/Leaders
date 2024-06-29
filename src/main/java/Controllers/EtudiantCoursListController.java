package Controllers;

import Models.Cours;
import Models.Inscription;
import Models.User;
import Services.ServiceCours;
import Services.ServiceInscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EtudiantCoursListController {
    @FXML
    private VBox coursesVBox;

    @FXML
    private AnchorPane main_form;
    private Parent root;
    private ServiceCours serviceCours;
    private ServiceInscription serviceInscription;
    public EtudiantCoursListController() {
        this.serviceCours = new ServiceCours();
        this.serviceInscription = new ServiceInscription();
    }
    @FXML
    public void initialize() {
        ServiceCours serviceCours = new ServiceCours();
        List<Cours> courses = serviceCours.getAll();
        displayCourses(courses);
    }

    private void displayCourses(List<Cours> courses) {
        coursesVBox.getChildren().clear();
        for (Cours course : courses) {
            HBox card = createCourseCard(course);
            card.setOnMouseClicked(event -> handleCardClick(course));
            coursesVBox.getChildren().add(card);
        }
    }



    private VBox createPaymentPrompt(Cours course) {
        VBox promptBox = new VBox(10);
        promptBox.setStyle("-fx-padding: 10px;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");
        promptBox.setPrefWidth(400);

        Label titleLabel = new Label(course.getTitre());
        titleLabel.setStyle("-fx-font-size: 16px;");

        Label payLabel = new Label("Please pay to access this course.");

        promptBox.getChildren().addAll(titleLabel, payLabel);

        return promptBox;
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
        titleLabel.setStyle("-fx-font-size: 16px;");

        Label descriptionLabel = new Label(course.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-font-size: 12px;");

        Label priceLabel = new Label("Price: "+course.getPrice());

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel,priceLabel);

        card.getChildren().addAll(imageView, detailsVBox);

        return card;
    }


    /*  private void handleCardClick(Cours course) {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cours.fxml"));
              Parent root = loader.load();

              CoursControllers controller = loader.getController();
              controller.initialize(course);

              coursesVBox.getScene().setRoot(root);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }*/
    private void handleCardClick(Cours course) {
        try {
            int userId = 2;
            ServiceInscription serviceInscription = new ServiceInscription();

            // Check if the user is enrolled in the course
            if (isEnrolled(course.getId(), userId)) {
                // Load the course view normally
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cours.fxml"));
                Parent root = loader.load();

                CoursControllers controller = loader.getController();
                controller.initialize(course);

                coursesVBox.getScene().setRoot(root);
            } else {
                // User is not enrolled, prompt for enrollment
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Enrollment Confirmation");
                alert.setHeaderText("You are not enrolled in this course.");
                alert.setContentText("Would you like to enroll now?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Proceed with enrollment
                    Inscription inscription = new Inscription(course.getId(), userId);
                    serviceInscription.addInscription(inscription);

                    // Optionally, you can then load the course view after enrollment
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cours.fxml"));
                    Parent root = loader.load();

                    CoursControllers controller = loader.getController();
                    controller.initialize(course);

                    coursesVBox.getScene().setRoot(root);
                } else {
                    // User chose not to enroll, do nothing or provide feedback
                    System.out.println("User chose not to enroll.");
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean isEnrolled(int courseId, int userId) {
        try {
            ServiceInscription serviceInscription = new ServiceInscription(); // Initialize your service
            List<Inscription> inscriptions = serviceInscription.getAllInscriptions(); // Get all inscriptions

            // Check if there is an inscription matching the courseId and userId
            for (Inscription inscription : inscriptions) {
                if (inscription.getCoursId() == courseId && inscription.getUtilisateurId() == userId) {
                    return true; // User is enrolled in the course
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions or return false if unable to determine enrollment status
        }
        return false; // User is not enrolled in the course
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

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent actionEvent) {
    }

    public void goToCoursesList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantCoursList.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
