package Controllers;

import Models.Evenement;
import utils.DatabaseHelper; // Importer DatabaseHelper depuis le package utils
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


public class AfficherEv implements Initializable {

    @FXML
    private TableColumn<Evenement, String> Title;

    @FXML
    private TableColumn<Evenement, String> desc;

    @FXML
    private TableColumn<Evenement, Long> id;

    @FXML
    private TableView<Evenement> tableview;

    @FXML
    private Label titre;

    @FXML
    private TextField eventNameField;

    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button qr;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Utiliser les noms corrects des propriétés
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        Title.setCellValueFactory(new PropertyValueFactory<>("titre"));

        // Remplir la table avec les données de la base de données
        ObservableList<Evenement> list = DatabaseHelper.getEvenements();
        tableview.setItems(list);
    }

    @FXML
    public void generateQR(ActionEvent actionEvent) {
        String eventName = eventNameField.getText();
        // Logic to generate QR code based on eventName
    }

    @FXML
    public void handleGenerateQR(ActionEvent event) {
        String eventName = eventNameField.getText();
        if (eventName != null && !eventName.isEmpty()) {
            try {
                Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
                hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                BitMatrix matrix = new MultiFormatWriter().encode(
                        new String(eventName.getBytes("UTF-8"), "UTF-8"),
                        BarcodeFormat.QR_CODE, 200, 200, hashMap
                );

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
                Image qrImage = SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray())), null);
                qrCodeImageView.setImage(qrImage);

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ActionEvent actionEvent) {
    }

    public void minimize(ActionEvent actionEvent) {
    }

    public void switchForm(ActionEvent actionEvent) {
    }

    public void goToUsers(ActionEvent actionEvent) {
    }

    public void goToCoursesList(ActionEvent actionEvent) {
    }

    public void goToArticles(ActionEvent actionEvent) {
    }

    public void goToFormation(ActionEvent actionEvent) {
        
    }
}
