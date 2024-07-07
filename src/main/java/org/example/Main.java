package org.example;

import Services.FormationService;
import Models.Formation;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        FormationService formationService = new FormationService();

        // Test adding formations
        Formation formation1 = new Formation("Formation Java", "Description de la formation Java");
        Formation formation2 = new Formation("Formation Python", "Description de la formation Python");



        formationService.addFormation(formation1);
        formationService.addFormation(formation2);

        // Test getting all formations
        System.out.println("All Formations:");
        formationService.getAllFormations().forEach(System.out::println);

        // Test getting a formation by ID
        int formationId = 1;
        Optional<Formation> formationById = formationService.getFormationById(formationId);
        formationById.ifPresentOrElse(
                formation -> System.out.println("Formation with ID " + formation.getId() + ": " + formation),
                () -> System.out.println("Formation with ID " + formationId + " not found")
        );

        // Test updating a formation
        formationById.ifPresent(formation -> {
            formation.setTitre("Formation Java mise à jour");
            formation.setDescription("Description mise à jour de la formation Java");
            formationService.updateFormation(formation.getId(), formation);
            System.out.println("Updated Formation with ID " + formation.getId() + ": " + formation);
        });

        // Test deleting a formation
        int formationToDeleteId = 2;
        boolean deleted = formationService.deleteFormation(formationToDeleteId);
        if (deleted) {
            System.out.println("Formation with ID " + formationToDeleteId + " deleted successfully.");
        } else {
            System.out.println("Formation with ID " + formationToDeleteId + " not found.");
        }

        // Optionally, fetch and display all formations to verify deletion
        System.out.println("All Formations after deletion:");
        formationService.getAllFormations().forEach(System.out::println);
    }
}
