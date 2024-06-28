package Services;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class PaymentService {

    public void processPayment(String token, int amount, String currency) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) amount)
                .setCurrency(currency)
                .setSource(token) // Token obtained from Stripe.js or Elements
                .build();

        Charge charge = Charge.create(params);
        // Handle successful charge
        System.out.println("Charge successful: " + charge);
    }
}
