package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expirationField;

    @FXML
    private TextField cvcField;

    private double coursePrice;
    private int courseId;
    private int userId;
    private boolean paymentSuccessful = false;

    public void initialize(double coursePrice, int courseId, int userId) {
        this.coursePrice = coursePrice;
        this.courseId = courseId;
        this.userId = userId;
    }

    @FXML
    private void handlePay() {
        simulatePayment();
    }

    private void simulatePayment() {
        // Simulate successful payment
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Payment of $" + coursePrice + " was successful!");
        alert.showAndWait();

        // Proceed to process payment in EtudiantCoursListController
        EtudiantCoursListController controller = new EtudiantCoursListController();
        controller.processPayment(coursePrice, courseId, userId);

        // Close the payment window
        closePaymentWindow();
        Alert enrollmentAlert = new Alert(Alert.AlertType.INFORMATION);
        enrollmentAlert.setTitle("Enrollment Successful");
        enrollmentAlert.setHeaderText(null);
        enrollmentAlert.setContentText("You have successfully enrolled in the course!");
        enrollmentAlert.showAndWait();
    }

    @FXML
    private void handleCancel() {
        // Handle cancel action
        closePaymentWindow();
    }

    private void closePaymentWindow() {
        Stage stage = (Stage) cardNumberField.getScene().getWindow();
        stage.close();
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }
}
