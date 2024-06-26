package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
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
    private TableColumn<Cours, String> colTitre;

    @FXML
    private TableColumn<Cours, String> coldesc;

    @FXML
    private TableColumn<Cours, String> colVideo;

    @FXML
    private TextField descTF;

    @FXML
    private TextField titreF;

    @FXML
    private TextField vidTF;

    @FXML
    private TextField imgTF;

    @FXML
    private TableView<Cours> tableView;

    @FXML
    private Button imgBTN;

    @FXML
    private Button vidBTN;

    private ObservableList<Cours> observableList;
    private ServiceCours sc;
    private Cloudinary cloudinary;

    private String uploadedImageUrl;
    private String uploadedVideoUrl;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void addCours(ActionEvent event) throws SQLException {
        String titre = titreF.getText().trim();
        String description = descTF.getText().trim();

        // Validate input fields
        if (titre.isEmpty() || description.isEmpty() || uploadedVideoUrl == null || uploadedImageUrl == null) {
            showAlert("Validation Error", "All fields must be filled out and files must be uploaded.");
            return;
        }

        Cours cours = new Cours(titre, description, uploadedVideoUrl, uploadedImageUrl);
        sc.add(cours);

        // Clear the text fields after adding the course
        titreF.clear();
        descTF.clear();
        vidTF.clear();
        imgTF.clear();

        // Clear the uploaded URLs
        uploadedImageUrl = null;
        uploadedVideoUrl = null;

        // Update the ObservableList and refresh the TableView
        observableList.add(cours);
        tableView.refresh();
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
                showAlert("Upload Error", "Failed to upload image.");
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
                showAlert("Upload Error", "Failed to upload video.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        sc = new ServiceCours();
        cloudinary = CloudinaryConfig.getCloudinary();
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVideo.setCellValueFactory(new PropertyValueFactory<>("video"));

        // Initialize the ObservableList and load data into it
        updateTableView();
    }

    private void updateTableView() {
        List<Cours> cs = sc.getAll();
        observableList = FXCollections.observableList(cs);
        tableView.setItems(observableList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void viewCourseDetails(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
       stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }


}
