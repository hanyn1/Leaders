package Controllers;

import Models.Article;
import Services.ServiceArticle;
import com.beust.ah.A;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import javafx.fxml.FXML;
import org.controlsfx.control.Rating;






public class ManageArticlesController {
    @FXML
    private Button Comment;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField contentField;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, Integer> idColumn;
    @FXML
    private TableColumn<Article, String> titleColumn;
    @FXML
    private TableColumn<Article, String> descriptionColumn;
    @FXML
    private TableColumn<Article, String> contentColumn;
    @FXML
    private TableColumn<Article, Timestamp> dateColumn;
    @FXML
    private Rating Rate;
    private ObservableList<Article> articleData = FXCollections.observableArrayList();
    private ServiceArticle serviceArticle;
    private Article selectedArticle;
    @FXML
    private AnchorPane main_form;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public ManageArticlesController() {
        try {
            serviceArticle = new ServiceArticle();
            articleData.addAll(serviceArticle.getAllArticles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void comment() {
        if (selectedArticle != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CommentaireAdd.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Ajouter un Commentaire");

                ArticleCommentController controller = loader.getController();
                controller.setArticle(selectedArticle);

                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(Comment.getScene().getWindow());
                stage.showAndWait();

                // Rafraîchir la table des articles ou des commentaires si nécessaire
                articlesTable.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        articlesTable.setItems(articleData);

        addButton.setOnAction(event -> addArticle());
        updateButton.setOnAction(event -> openEditWindow());
        deleteButton.setOnAction(event -> deleteArticle());

        articlesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectArticle(newValue));
    }

    @FXML
    private void addArticle() {
        try {
            Article article = new Article();
            article.setTitre(titleField.getText());
            article.setDescription(descriptionField.getText());
            article.setContenu(contentField.getText());
            article.setDateCreation(new Timestamp(System.currentTimeMillis())); // Setting the current timestamp

            serviceArticle.addArticle(article);
            articleData.add(article);
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEditWindow() {
        if (selectedArticle != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ArticleEdit.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Edit Article");

                ArticleEditController controller = loader.getController();
                controller.setServiceArticle(serviceArticle);
                controller.setArticle(selectedArticle);

                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(updateButton.getScene().getWindow());
                stage.showAndWait();

                articlesTable.refresh();
                clearForm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteArticle() {
        if (selectedArticle != null) {
            try {
                serviceArticle.deleteArticle(selectedArticle.getId());
                articleData.remove(selectedArticle);
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectArticle(Article article) {
        if (article != null) {
            selectedArticle = article;
            titleField.setText(article.getTitre());
            descriptionField.setText(article.getDescription());
            contentField.setText(article.getContenu());
        }
    }

    private void clearForm() {
        titleField.clear();
        descriptionField.clear();
        contentField.clear();
        selectedArticle = null;
    }
    @FXML
    public void goToCoursesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AjouterCoursAdmin.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }@FXML
    public void goToUsers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RoleCRUD.fxml")));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomeToEv.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToQuizz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/QUIZZview.fxml")));
        stage =(Stage)( (Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminDashboard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToRole(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RoleCRUD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*
    @FXML
    private Label label;
    @FXML
    private Rating tacoRation;
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("Rating given by user:"+myRating.getRating());
    }*/
}
