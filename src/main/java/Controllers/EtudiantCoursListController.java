package Controllers;

import Models.Cours;
import Models.Inscription;
import Services.ServiceCours;
import Services.ServiceInscription;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class EtudiantCoursListController {
    @FXML
    private VBox coursesVBox;
    @FXML
    private AnchorPane main_form;
    private Parent root;
    private ServiceCours serviceCours;
    private ServiceInscription serviceInscription;
    private int userId;
    @FXML
    private Spinner<Cours> spinner;

    public EtudiantCoursListController() {
        this.serviceCours = new ServiceCours();
        this.serviceInscription = new ServiceInscription();
        this.userId = 2;
    }

    @FXML
    public void initialize() {
        System.out.println("Initializing EtudiantCoursListController...");
        spinner.setVisible(true);
        loadCourses();
    }

    public void loadCourses() {
        System.out.println("Loading courses...");
        List<Cours> courses = serviceCours.getAll();
        displayCourses(courses);
        spinner.setVisible(false);
    }

    private void displayCourses(List<Cours> courses) {
        coursesVBox.getChildren().clear();
        for (Cours course : courses) {
            HBox card = createCourseCard(course);
            card.setOnMouseClicked(event -> handleCardClick(course));
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
            System.out.println("No image available for course: " + course.getTitre());
        }

        VBox detailsVBox = new VBox(5);
        Label titleLabel = new Label(course.getTitre());
        titleLabel.setStyle("-fx-font-size: 16px;");

        Label descriptionLabel = new Label(course.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-font-size: 12px;");

        Label priceLabel = new Label("Price: " + course.getPrice());
        Label expiredLabel = new Label("Expired");
        Inscription inscription = getInscription(course.getId(), userId);
        if (inscription != null && inscription.getExpirationDate() != null) {
            if (inscription.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
                expiredLabel.setVisible(true);
                expiredLabel.setStyle("-fx-text-fill: red;");
            } else {
                expiredLabel.setVisible(false);
            }
        } else {
            expiredLabel.setVisible(false);
        }


        Button enrollBtn = new Button();
        if (isEnrolled(course.getId(), userId)) {
            enrollBtn.setText("Enrolled");
            enrollBtn.setStyle("-fx-background-color: #8f8f8f; -fx-text-fill: #fff;");
        } else {
           // Inscription inscription = getInscription(course.getId(), userId);
            if (inscription != null && inscription.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
                enrollBtn.setText("Enroll - Pay for a Month!");
                enrollBtn.setStyle("-fx-background-color: red; -fx-text-fill: #fff;");
            } else {
                enrollBtn.setText("Enroll now For A Full Month!");
                enrollBtn.setStyle("-fx-background-color: green; -fx-text-fill: #fff;");
            }
        }

        detailsVBox.getChildren().addAll(titleLabel, descriptionLabel, priceLabel,enrollBtn,expiredLabel);
        card.getChildren().addAll(imageView, detailsVBox);

        return card;
    }

    private void handleCardClick(Cours course) {
        System.out.println("Handling card click for course: " + course.getTitre());
        try {
            Inscription inscription = getInscription(course.getId(), userId);
            if (inscription != null) {
                if (inscription.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
                    System.out.println("User's enrollment has expired for course: " + course.getTitre());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Enrollment Expired");
                    alert.setHeaderText(null);
                    alert.setContentText("Your enrollment for this course has expired. Please make a payment to re-enroll.");
                    alert.showAndWait();
                    initiatePayment(course);
                } else {
                    System.out.println("User is already enrolled in course: " + course.getTitre());
                    loadCourseView(course.getId());
                }
            } else {
                System.out.println("User is not enrolled in course: " + course.getTitre());
                initiatePayment(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Inscription getInscription(int courseId, int userId) {
        try {
            List<Inscription> inscriptions = serviceInscription.getAllInscriptions();
            for (Inscription inscription : inscriptions) {
                if (inscription.getCoursId() == courseId && inscription.getUtilisateurId() == userId) {
                    return inscription;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void initiatePayment(Cours course) throws IOException {
        System.out.println("Initiating payment for course: " + course.getTitre());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Payment.fxml"));
        Parent root = loader.load();

        PaymentController controller = loader.getController();
        controller.initialize(course.getPrice(), course.getId(), userId);

        Stage stage = new Stage();
        stage.setTitle("Payment");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnHiding(event -> {
            if (controller.isPaymentSuccessful()) {
                // Add inscription for the user after successful payment
                Timestamp expirationDate = Timestamp.valueOf(LocalDateTime.now().plusMonths(1)); // Set expiration date to 1 month from now
                Inscription inscription = new Inscription(course.getId(), userId, expirationDate);
             /*   try {
                    //serviceInscription.addInscription(inscription);
                    // Load the course view after successful payment and inscription
                   // loadCourseView(course.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle exception during inscription or loading course view
                }*/
            }
        });
    }

    private boolean isEnrolled(int courseId, int userId) {
        try {
            List<Inscription> inscriptions = serviceInscription.getAllInscriptions();
            for (Inscription inscription : inscriptions) {
                if (inscription.getCoursId() == courseId && inscription.getUtilisateurId() == userId) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void processPayment(double amount, int courseId, int userId) {
        System.out.println("Processing payment for course ID: " + courseId + " with amount: " + amount);
        try {
            Stripe.apiKey = "sk_test_51PNsyPECUf2gUGa0EACGktrojtWtJn6dHOig03DgMAKzCBNsiSDddDNR4NxFF13YYPXBEemzydvGecPdWX3QtkK100tj56u2XO";

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount((long) (amount * 100))
                    .setDescription("Enrollment fee for Course ID: " + courseId)
                    .addPaymentMethodType("card")
                    .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);

            // Simulate obtaining the payment method ID from the PaymentController
            // In a real scenario, you would obtain this ID from user input in the PaymentController
            String paymentMethodId = "pm_card_visa"; // This is a placeholder. Replace with actual payment method ID

            PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                    .setPaymentMethod(paymentMethodId) // Use the actual payment method ID here
                    .build();

            intent = intent.confirm(confirmParams);

            if ("requires_capture".equals(intent.getStatus())) {
                // Payment is successful and ready for capture
                System.out.println("Payment successful for course ID: " + courseId);
                capturePayment(intent.getId());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Payment Successful");
                alert.setHeaderText(null);
                alert.setContentText("Payment successful! You are now enrolled in the course.");
                alert.showAndWait();

                // Add inscription for the user after successful payment
              /*  Timestamp expirationDate = Timestamp.valueOf(LocalDateTime.now().plusMonths(1)); // Set expiration date to 1 month from now
                Inscription inscription = new Inscription(courseId, userId, expirationDate);
                serviceInscription.addInscription(inscription);*/
                Inscription inscription = getInscription(courseId, userId);

                // If inscription exists, update expiration date
                if (inscription != null) {
                    Timestamp newExpirationDate = Timestamp.valueOf(LocalDateTime.now().plusMonths(1));
                    inscription.setExpirationDate(newExpirationDate);
                    serviceInscription.update(inscription);
                    System.out.println("Expiration date updated for inscription: " + inscription.getId());
                } else {
                    // If inscription does not exist, add new inscription
                    Timestamp expirationDate = Timestamp.valueOf(LocalDateTime.now().plusMonths(1));
                    Inscription newInscription = new Inscription(courseId, userId, expirationDate);
                    serviceInscription.addInscription(newInscription);
                    System.out.println("New inscription added for course ID: " + courseId);
                }

                // Load the course view
                loadCourseView(courseId);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Payment Error");
                alert.setHeaderText(null);
                alert.setContentText("Payment was not successful. Status: " + intent.getStatus());
                alert.showAndWait();
            }
        } catch (StripeException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred during payment processing. Please try again later.");
            alert.showAndWait();
        }
    }


    private void capturePayment(String paymentIntentId) {
        try {
            PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
            intent.capture();
            System.out.println("Payment captured for payment intent ID: " + paymentIntentId);
        } catch (StripeException e) {
            e.printStackTrace();
            // Handle error during payment capture
        }
    }


    private void loadCourseView(int courseId) {
        System.out.println("Loading course view for course ID: " + courseId);
        try {
            if (coursesVBox != null && coursesVBox.getScene() != null) {
                Cours course = serviceCours.getCoursById(courseId);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EtudiantCours.fxml"));
                Parent root = loader.load();

                EtudiantCoursController controller = loader.getController();
                controller.initialize(course);

                coursesVBox.getScene().setRoot(root);
            } else {
                System.err.println("coursesVBox or its scene is null.");
            }
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

    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EtudiantDashboard.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
