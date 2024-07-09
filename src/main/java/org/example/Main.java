package org.example;

import Models.Evenement;
import Models.RessourceEv;
import Services.EvenementService;
import Services.RessourceEvService;
import utils.MyConfig;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException {
        MyConfig myConfig = MyConfig.getInstance();
        System.out.println("Hello and welcome!");

        // Partie gestion des événements
        gestionEvenements();

        // Partie gestion des ressources événementielles
        gestionRessourcesEv();
    }

    public static void gestionEvenements() throws SQLException {
        EvenementService evenementService = new EvenementService();

        // Ajout de quelques événements
        Evenement evenement1 = new Evenement(1, "Conférence A", "Description de la conférence A");
        Evenement evenement2 = new Evenement(2, "Festival B", "Description du festival B");
        Evenement evenement3 = new Evenement(3, "Exposition C", "Description de l'exposition C");
        Evenement evenement4 = new Evenement(4, "Festival D", "Description du festival D");
        Evenement evenement5 = new Evenement(5, "Conférence E", "Description de la conférence E");
        Evenement evenement6 = new Evenement(6, "Exposition F", "Description de l'exposition F");

        // Ajout des événements
        evenementService.ajouter(evenement1);
        evenementService.ajouter(evenement2);
        evenementService.ajouter(evenement3);
        evenementService.ajouter(evenement4);
        evenementService.ajouter(evenement5);
        evenementService.ajouter(evenement6);

        // Modification d'un événement
        evenement1.setTitre("Conférence A modifiée");
        evenementService.modifier(evenement1);

        // Suppression d'un événement spécifique par ID
        evenementService.supprimer(evenement2.getId());

        // Récupération de tous les événements
        List<Evenement> evenements = evenementService.recuperer();

        // Affichage des événements récupérés
        for (Evenement evenement : evenements) {
            System.out.println(evenement);
        }

        // Classification des événements par titre
        Map<String, List<Evenement>> categorizedEvents = categorizeEventsByTitle(evenements);

        // Affichage des événements classés par titre
        for (Map.Entry<String, List<Evenement>> entry : categorizedEvents.entrySet()) {
            System.out.println("Titre : " + entry.getKey());
            for (Evenement evenement : entry.getValue()) {
                System.out.println(" - " + evenement.getTitre());
            }
        }

        // Génération du fichier FXML avec les données classifiées
        generateFXMLFile(categorizedEvents);
    }

    public static void gestionRessourcesEv() throws SQLException {
        RessourceEvService ressourceEvService = new RessourceEvService();

        RessourceEv ressourceEv1 = new RessourceEv(2, "Titre1", "Description1", "http://example.com/1", new Timestamp(System.currentTimeMillis()));
        RessourceEv ressourceEv2 = new RessourceEv(5, "Titre2", "Description2", "http://example.com/2", new Timestamp(System.currentTimeMillis()));

        ressourceEvService.ajouter(ressourceEv1);
        ressourceEvService.ajouter(ressourceEv2);

        ressourceEv1.setTitre("Titre modifié");
        ressourceEvService.modifier(ressourceEv1);

        // Suppression d'une ressource spécifique par ID
        ressourceEvService.supprimer(ressourceEv1.getId());

        List<RessourceEv> ressources = null;
        try {
            ressources = ressourceEvService.recuperer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (RessourceEv ressource : ressources) {
            System.out.println(ressource);
        }
    }

    public static Map<String, List<Evenement>> categorizeEventsByTitle(List<Evenement> events) {
        Map<String, List<Evenement>> categorizedEvents = new HashMap<>();

        for (Evenement event : events) {
            String title = event.getTitre();
            categorizedEvents.computeIfAbsent(title, k -> new ArrayList<>()).add(event);
        }

        return categorizedEvents;
    }

    public static void generateFXMLFile(Map<String, List<Evenement>> categorizedEvents) {
        // Ici, vous implémenterez la logique pour générer le fichier FXML
        // Cette partie dépend de votre cadre de développement (JavaFX, etc.)
        // Voici un exemple très basique de sortie
        System.out.println("Génération du fichier FXML...");

        // Exemple de sortie des données dans le fichier FXML
        for (Map.Entry<String, List<Evenement>> entry : categorizedEvents.entrySet()) {
            System.out.println("Titre : " + entry.getKey());
            for (Evenement event : entry.getValue()) {
                System.out.println(" - " + event.getTitre());
                // Ici, vous écririez dans votre fichier FXML en fonction de votre besoin
                // Par exemple, créer des éléments de vue JavaFX
            }
        }

        System.out.println("Fichier FXML généré avec succès.");
    }
}
