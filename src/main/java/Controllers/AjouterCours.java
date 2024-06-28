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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private AnchorPane main_form;
    private ObservableList<Cours> coursList = FXCollections.observableArrayList();

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



    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent actionEvent) {
    }

    public void goToCoursesList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage =(Stage)( (Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void deleteCours() {
        Cours selectedCours = tableView.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Course");
            alert.setContentText("Are you sure you want to delete the selected course?");

            // Show the confirmation dialog and wait for user response
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // User clicked OK, proceed with deletion
                    tableView.getItems().remove(selectedCours);
                    try {
                        ServiceCours serviceCours = new ServiceCours();
                        serviceCours.delete(selectedCours.getId());
                        System.out.println("Course deleted from database successfully!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // User clicked Cancel or closed the dialog, do nothing
                    System.out.println("Deletion canceled.");
                }
            });
        } else {
            // No course selected, handle accordingly
            System.out.println("No course selected.");
        }
    }


    @FXML
    public void updateCours() {
        Cours selectedCours = tableView.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCours.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.initModality(Modality.APPLICATION_MODAL);
                EditCoursController controller = loader.getController();
                controller.setCours(selectedCours);
                stage.showAndWait();
                tableView.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
