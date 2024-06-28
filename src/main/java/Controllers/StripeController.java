package Controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

public class StripeController {

    @FXML
    private Button payButton;

    public StripeController() {
        Stripe.apiKey = "sk_test_51PNsyPECUf2gUGa0EACGktrojtWtJn6dHOig03DgMAKzCBNsiSDddDNR4NxFF13YYPXBEemzydvGecPdWX3QtkK100tj56u2XO";
    }

    @FXML
    private void handlePayButtonAction() {
        String sessionId = createStripeSession();
        if (sessionId != null) {
            displayStripeCheckout(sessionId);
            startPolling(sessionId);
        }
    }

    private String createStripeSession() {
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(2000L) // Amount in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("T-shirt")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .setSuccessUrl("http://localhost:4242/success") // Replace with your success URL
                .setCancelUrl("http://localhost:4242/cancel") // Replace with your cancel URL
                .build();

        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void displayStripeCheckout(String sessionId) {
        Stage stage = new Stage();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://checkout.stripe.com/pay/" + sessionId);

        VBox root = new VBox(webView);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Stripe Checkout");
        stage.setScene(scene);
        stage.show();
    }

    private void startPolling(String sessionId) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Session session = Session.retrieve(sessionId);
                    if ("complete".equals(session.getStatus())) {
                        Platform.runLater(() -> {
                            System.out.println("Payment Successful");
                            // Show success message to user
                        });
                        timer.cancel();
                    }
                } catch (StripeException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000); // Poll every 5 seconds
    }
}
