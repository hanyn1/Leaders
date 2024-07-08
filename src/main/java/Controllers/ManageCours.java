package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import Models.Cours;
import Services.ServiceCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ManageCours {
    @FXML
    private Label contentLabel;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private VBox recentCoursesVBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cours> tableView;

    @FXML
    private TableColumn<Cours, String> colTitre;

    @FXML
    private TableColumn<Cours, String> coldesc;

    @FXML
    private TableColumn<Cours, String> colVideo;

    private ObservableList<Cours> observableList;
    private ServiceCours sc;

   @FXML
   void initialize() {
       sc = new ServiceCours();
       colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
       colVideo.setCellValueFactory(new PropertyValueFactory<>("video"));

       // Initialize the ObservableList and load data into it
       updateTableView();
   }
    @FXML
    public void goToHome(ActionEvent actionEvent) {
        contentLabel.setText("Home Content Goes Here");
    }
    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/CoursList.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToManageCours(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageCours.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/InstructorDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UserCRUD.fxml")));
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


    public void goToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ManageArticle.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void goToFormation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FormationController.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterEv.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void updateTableView() {
        List<Cours> cs = sc.getAll();
        observableList = FXCollections.observableList(cs);
        tableView.setItems(observableList);
    }

    @FXML
    private void handleAddCourse(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCours.fxml"));
            Parent root = loader.load();
            stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
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
}
