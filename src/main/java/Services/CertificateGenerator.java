package Services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.imageio.ImageIO;

public class CertificateGenerator {

    public String generateCertificate(String userName, String courseTitle, LocalDate completionDate) {
        int width = 800;
        int height = 600;
        BufferedImage certificate = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = certificate.createGraphics();

        // Background color
        g2d.setColor(Color.decode("#FFFFFF"));
        g2d.fillRect(0, 0, width, height);

        // Borders
        g2d.setColor(Color.decode("#4e4406"));
        g2d.drawRect(10, 10, width - 20, height - 20);

        // Load logo image
        BufferedImage logo = null;
        try {
            logo = ImageIO.read(new File("src/main/resources/images/logo-trans.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Draw logo image
        if (logo != null) {
            int logoWidth = 212;
            int logoHeight = 71;
            g2d.drawImage(logo, 20, 20, logoWidth, logoHeight, null);
        }

        // Title
        g2d.setFont(new Font("Lato", Font.BOLD, 36));
        g2d.setColor(Color.decode("#4e4406"));
        g2d.drawString("Certificate of Completion", 150, 150);

        // User Name
        g2d.setFont(new Font("Lato", Font.PLAIN, 24));
        g2d.drawString("This is to certify that", 250, 200);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(userName, 250, 250);

        // Course Title
        g2d.setFont(new Font("Lato", Font.PLAIN, 24));
        g2d.drawString("has successfully completed the course", 180, 300);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(courseTitle, 200, 350);

        // Completion Date
        g2d.setFont(new Font("Lato", Font.PLAIN, 24));
        g2d.drawString("on " + completionDate.toString(), 300, 400);

        // Congratulatory Message
        g2d.setFont(new Font("Lato", Font.PLAIN, 20));
        g2d.drawString("Congratulations on your achievement!", 200, 450);

        g2d.dispose();

        // Save the certificate as an image
        String filePath = "certificates/" + userName.replace(" ", "_") + "_Certificate.png";
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            ImageIO.write(certificate, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
