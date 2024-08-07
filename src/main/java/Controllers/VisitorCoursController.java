package Controllers;

import Models.Cours;
import Services.ServiceCours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class VisitorCoursController {

    @FXML
    private VBox coursesVBox;

    @FXML
    private AnchorPane main_form;

    @FXML
    public void initialize() {
        System.out.println("Initializing VisitorCoursController");
        ServiceCours serviceCours = new ServiceCours();
        List<Cours> courses = serviceCours.getAll();
        System.out.println("Fetched courses: " + courses.size());
        displayCourses(courses);
    }

    private void displayCourses(List<Cours> courses) {
        System.out.println("Displaying courses");
        coursesVBox.getChildren().clear(); // Clear existing content
        for (Cours course : courses) {
            System.out.println("Creating card for course: " + course.getTitre());
            HBox card = createCourseCard(course);
            card.setOnMouseClicked(event -> {
                System.out.println("Course card clicked: " + course.getTitre());
                try {
                    gotLogin(event); // Redirect to login page on card click
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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
        Button enrollBtn = new Button();
        enrollBtn.setStyle("-fx-background-color: green; -fx-text-fill: #fff;");
        enrollBtn.setText("Enroll now!");
        Label priceLabel = new Label("Price: " + course.getPrice());
        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel,enrollBtn, priceLabel);

        card.getChildren().addAll(imageView, detailsVBox);

        return card;
    }

    public void close(ActionEvent actionEvent) {
        System.out.println("Closing application");
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        System.out.println("Minimizing window");
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void gotLogin(MouseEvent event) throws IOException {
        System.out.println("Redirecting to login page");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("Redirecting to home page");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorPage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToCoursesList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorCours.fxml")));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToArticleList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VisitorArticle.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
