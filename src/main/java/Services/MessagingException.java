package Services;
javax.mail.MessagingException;

public class MessagingException {

    public void printStackTrace(String recipient, String subject, String body) throws MessagingException {
        // Email sending logic...

        // Handle potential exceptions
        if (){
            throw new MessagingException("Failed to send email!");
        }
    }
}