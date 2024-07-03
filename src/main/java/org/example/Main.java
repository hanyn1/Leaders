package org.example;

import Models.Formation;
import Services.ServiceFormation;
import Services.ServiceInscription;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Adding CRUD operations for Formation
        ServiceFormation serviceFormation = new ServiceFormation();
        ServiceInscription serviceInscription = new ServiceInscription();

        int courseId = 1;
        int userId = 2;
       System.out.println(serviceInscription.getInscriptionByCourseAndUser(courseId, userId));

        System.out.println(serviceInscription.getAllInscriptions());
        // Test adding formations
       /* Formation formation1 = new Formation(1, "Formation Java", "Description de la formation Java");
        Formation formation2 = new Formation(2, "Formation Python", "Description de la formation Python");


        serviceFormation.addFormation(formation1);
        serviceFormation.addFormation(formation2);

        // Test getting all formations
        System.out.println("All Formations:");
        serviceFormation.getAllFormations().forEach(System.out::println);

        // Test getting a formation by ID
        int formationId = 1;
        Optional<Formation> formationById = serviceFormation.getFormationById(formationId);
        formationById.ifPresentOrElse(
                formation -> System.out.println("Formation with ID " + formation.getId() + ": " + formation),
                () -> System.out.println("Formation with ID " + formationId + " not found")
        );

        // Test updating a formation
        formationById.ifPresent(formation -> {
            formation.setTitre("Formation Java mise à jour");
            formation.setDescription("Description mise à jour de la formation Java");
            serviceFormation.updateFormation(formation.getId(), formation);
            System.out.println("Updated Formation with ID " + formation.getId() + ": " + formation);
        });

        // Test deleting a formation
        int formationToDeleteId = 2;
        boolean deleted = serviceFormation.deleteFormation(formationToDeleteId);
        if (deleted) {
            System.out.println("Formation with ID " + formationToDeleteId + " deleted successfully.");
        } else {
            System.out.println("Formation with ID " + formationToDeleteId + " not found.");
        }*/
    }
}
