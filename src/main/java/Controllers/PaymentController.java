package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        // Set up listeners for input validation
        setupInputValidation();
    }

    private void setupInputValidation() {
        // Validate card number input
        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,16}")) {
                cardNumberField.setText(oldValue);
            }
        });

        // Validate expiration date input (assuming MM/YY format)
        expirationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}")) {
                expirationField.setText(oldValue);
            }
        });

        // Validate CVC input
        cvcField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                cvcField.setText(oldValue);
            }
        });
    }

    @FXML
    private void handlePay() {
        if (validateFields()) {
            simulatePayment();
        } else {
            showAlert("Invalid Input", "Please correct the input fields.");
        }
    }

    private boolean validateFields() {
        // Validate each field for the required number of digits
        return cardNumberField.getText().matches("\\d{16}")
                && expirationField.getText().matches("\\d{4}")
                && cvcField.getText().matches("\\d{3}");
    }

    private void simulatePayment() {
        // Simulate successful payment
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Payment of $" + coursePrice + " is in processing!");
        alert.showAndWait();

        // Proceed to process payment in EtudiantCoursListController
        EtudiantCoursListController controller = new EtudiantCoursListController();
        controller.processPayment(coursePrice, courseId, userId);

        // Close the payment window
        closePaymentWindow();
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }
}

