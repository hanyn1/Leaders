package Controllers;
import Models.Article;
import Services.ServiceArticle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;

public class AjouterArticle {
    private final ServiceArticle pc = new ServiceArticle();

        @FXML
        private TextField fxtitre;

        @FXML
        private TextField fxdescription;
        @FXML
        private TextField fxcontenue;

        @FXML
        private Date fxdate;

        @FXML
        void AjouterArt(ActionEvent event) {
            pc.add(new Article(fxtitre.getText(),fxdescription.getText(),fxcontenue.getText(), fxdate.getDate()));
            /* {Article article = new Article();
                article.setTitre(fxtitre.getText());
                article.setContenu(fxdescription.getText());
                article.setDescription(fxcontenue.getText());
                article.setDatePublication(fxdate.getDate());
                pc.add(article);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }*/

        }


    @FXML
    void naviguezVersAffichage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherArticle.fxml"));
            fxtitre.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
/*
  public void afficherPersonne(ActionEvent event) throws IOException {
              /*  public void afficherPersonne(ActionEvent event)
        {
           FXMLLoader loader = new FXMLLoader("ressources.AfficherArticle");
    }


      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ressources.AfficherArticle.fxml"));
      Parent root = loader.load();

  }  */

