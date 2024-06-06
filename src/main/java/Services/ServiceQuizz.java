package Services;

import Interfaces.QuizzInterface;
import Models.Quizz;
import com.mysql.cj.Session;
import com.sun.jdi.connect.Transport;
import utils.MyConfig;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ServiceQuizz implements QuizzInterface<Quizz> {

    private MyConfig instance = MyConfig.getInstance();
    private Connection connection;

    public ServiceQuizz() {
        this.connection = instance.getConnection();
        System.out.println("ServiceQuizz initialized");
    }

    // Add a new Quizz
    public void addQuizz(Quizz q) {
        String req = "INSERT INTO Quizz(id, titre, description, date) VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
           
            ps.setInt(1, q.getId());
            ps.setString(2, q.getTitre());
            ps.setString(3, q.getDescription());
            Date datequizz = new Date(2024, 12, 30);
            ps.setDate(4, new Date(datequizz.getDate()));

            ps.executeUpdate();
            System.out.println("Quizz added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all Quizzes
    public List<Quizz> getAllQuizzes() {
        List<Quizz> quizzes = new ArrayList<>();
        String req = "SELECT id, titre, description, date FROM Quizz";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                Quizz quizz = new Quizz(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
                quizzes.add(quizz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    // Get a Quizz by ID
    public Quizz getQuizzById(int id) {
        Quizz quizz = null;
        String req = "SELECT id, titre, description, date FROM Quizz WHERE id = 1";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quizz = new Quizz(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizz;
    }

    // Update an existing Quizz
    public void updateQuizz(Quizz q) throws SQLException {
        {
            String req = "UPDATE Quizz SET titre = ?, description = ?, date = ? WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(req)) {
                ps.setString(1, q.getTitre());
                ps.setString(2, q.getDescription());
                ps.setDate(3, new Date(q.getQuizzDate().getTime()));
                ps.setInt(4, q.getId());

                ps.executeUpdate();
                System.out.println("Quizz updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        // Delete a Quizz by ID
    }

    @Override
    public void deleteQuizz(int id) throws SQLException {
        String req = "DELETE FROM Quizz WHERE id = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(req)) {
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Quizz deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public class EmailNotificationAPI<MimeMessage> {

        private final String senderEmail;
        private final String senderPassword;
        private final String smtpServer;
        private final String subject;

        public EmailNotificationAPI(String senderEmail, String senderPassword, String smtpServer, String subject) {
            this.senderEmail = senderEmail;
            this.senderPassword = senderPassword;
            this.smtpServer = smtpServer;
            this.subject = subject;
        }

        public void sendNotificationEmail(String recipientEmail, String quizName, int attemptNumber) {
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpServer);
                props.put("mail.smtp.auth", "true");

                Session session;
                session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                MimeMessage message = new MimeMessage(session);
               message.SetFrom(new InternetAddress(senderEmail));
              message.addRecipient(message.RecipientType.TO,
                      new InternetAddress(recipientEmail));
                message.setSubject(subject);

                String messageContent = buildMessageContent(quizName, attemptNumber);
                message.setText(messageContent);

                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        private String buildMessageContent(String quizName, int attemptNumber) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Bonjour,\n\n")
                    .append("Nous vous informons que vous avez échoué au quizz '")
                    .append(quizName)
                    .append("' pour la ")
                    .append(attemptNumber)
                    .append("ème fois.\n\n")
                    .append("Nous vous recommandons de réviser le contenu du quizz et de réessayer dans un mois.\n\n")
                    .append("Cordialement,\n")
                    .append("L'équipe Quizz");
            return messageBuilder.toString();
        }
    }
}


