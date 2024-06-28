package Controllers;

import Services.PaymentService;
import com.stripe.exception.StripeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PaymentController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField amountField;

    @FXML
    private void handlePayment() {
        String cardNumber = cardNumberField.getText().replaceAll("\\s+", ""); // Remove spaces
        int amount = Integer.parseInt(amountField.getText()); // Parse amount from field

        PaymentService paymentService = new PaymentService();
        try {
            paymentService.processPayment(cardNumber, amount, "usd");
            showAlert("Payment Successful", "Payment was successful.");
        } catch (StripeException e) {
            showAlert("Payment Error", "Error processing payment: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
