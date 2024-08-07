package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import Models.Cours;
import Services.ServiceCours;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.CloudinaryConfig;

public class AjouterCours {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBTN;

    @FXML
    private TextField descTF;

    @FXML
    private TextField titreF;

    @FXML
    private TextField vidTF;

    @FXML
    private TextField imgTF;

    @FXML
    private TextField priceTF;

    @FXML
    private Button imgBTN;

    @FXML
    private Button vidBTN;

    private ServiceCours sc;
    private Cloudinary cloudinary;

    private String uploadedImageUrl;
    private String uploadedVideoUrl;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane main_form;
    private ObservableList<Cours> coursList = FXCollections.observableArrayList();

    @FXML
    void addCours(ActionEvent event) throws SQLException {
        String titre = titreF.getText().trim();
        String description = descTF.getText().trim();
        String priceText = priceTF.getText().trim(); // Get the text from priceTF and trim it

        // Validate input fields
        if (titre.isEmpty() || description.isEmpty() || priceText.isEmpty() || uploadedVideoUrl == null || uploadedImageUrl == null) {
            showAlertError("Validation Error", "All fields must be filled out and files must be uploaded.");
            return;
        }

        // Parse the price text to a float
        float price;
        try {
            price = Float.parseFloat(priceText);
        } catch (NumberFormatException e) {
            showAlertError("Validation Error", "Price must be a valid number.");
            return;
        }

        Cours cours = new Cours(titre, description, uploadedVideoUrl, uploadedImageUrl, price);
        sc.add(cours);

        // Clear the text fields after adding the course
        titreF.clear();
        descTF.clear();
        vidTF.clear();
        imgTF.clear();
        priceTF.clear();

        // Clear the uploaded URLs
        uploadedImageUrl = null;
        uploadedVideoUrl = null;
        showAlert("Success", "Course added successfully.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(selectedFile, ObjectUtils.emptyMap());
                uploadedImageUrl = (String) uploadResult.get("secure_url");
                imgTF.setText(uploadedImageUrl);
            } catch (IOException e) {
                showAlertError("Upload Error", "Failed to upload image.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void uploadVideo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv", "*.mov"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(selectedFile, ObjectUtils.asMap("resource_type", "video"));
                uploadedVideoUrl = (String) uploadResult.get("secure_url");
                vidTF.setText(uploadedVideoUrl);
            } catch (IOException e) {
                showAlertError("Upload Error", "Failed to upload video.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        sc = new ServiceCours();
        cloudinary = CloudinaryConfig.getCloudinary();

    }



    private void showAlertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void viewCourseDetails(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } @FXML
    public void goToAjouterCours(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterCours.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/InstructorDashboard.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void goToManageCourses(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageCours.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
