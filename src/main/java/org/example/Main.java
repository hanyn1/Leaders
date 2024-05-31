package org.example;

import Models.Evenement;
import Services.EvenementService;
import UTILS.MyConfig;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyConfig m = MyConfig.getInstance();
        System.out.println("Hello and welcome!");

        EvenementService ps = new EvenementService();
        //ps.ajouter(new Evenement(4, "TeamCommunication", "Yallah")); // Assurez-vous que l'ID 1 existe dans 'entité'
       ps.modifier(new Evenement(4, "TeamCommunication", "Yal")); // Assurez-vous que l'ID 1 existe dans 'entité'
        //ps.supprimer( 4); // Assurez-vous que l'ID 1 existe dans 'entité'

    }
}



