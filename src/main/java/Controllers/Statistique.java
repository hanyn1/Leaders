package Controllers;

import Models.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;
import java.util.Map;

public class Statistique {

    @FXML
    private ListView<String> eventsListView;

    // Méthode pour initialiser le contrôleur
    public void initialize(Map<String, List<Evenement>> categorizedEvents) {
        ObservableList<String> items = FXCollections.observableArrayList();

        // Parcourir les événements classés et ajouter chaque titre à la liste
        for (Map.Entry<String, List<Evenement>> entry : categorizedEvents.entrySet()) {
            String title = entry.getKey();
            items.add(title);
        }

        // Configurer la ListView pour afficher les titres des événements
        eventsListView.setItems(items);
        eventsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Gérer la sélection d'un titre pour afficher les détails correspondants si nécessaire
        eventsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // Ici vous pouvez ajouter le code pour afficher les détails des événements sélectionnés
                    System.out.println("Titre sélectionné : " + newValue);
                }
        );
    }
}