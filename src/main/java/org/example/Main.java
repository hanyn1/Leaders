package org.example;

import Models.*;
import utils.MyConfig;
import Services.*;

import java.sql.Date;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        /*MyConfig m= MyConfig.getInstance();
        System.out.printf("Hello and welcome!");

        ServiceArticle sa = new ServiceArticle();
        Article a1= new Article();
        // Article a1 = new Article(new Date(2002,12,23),"objet","produit1");
        // sa.add(a1);

        ServiceCours sc = new ServiceCours();

        // Creating a Cours object without id
        Cours c1 = new Cours("Linux", "Deep dive into Linux", "advanced_linux.mp4");
        Cours c2 = new Cours("Devops", "Deep dive into Devops", "advanced_devops.mp4");
        System.out.println(sc.getAll());
       // Date datePublication = new Date(2024, 12, 30);
       // ServiceArticle s1= new ServiceArticle();

        //s1.updateArticle(new Article(1,"nouveau titre","nouvelle description","nouveau contenu",datePublication));
        //System.out.println("article updated successfully.");

        //sc.add(c1);
        //sc.add(c2);
        //sc.update(c1);

        //System.out.println(si.getInscriptionById(i1));
        ServiceInscription si = new ServiceInscription();
        Inscription i1 = new Inscription(4,1);
        //si.addInscription(i1);
        System.out.println(si.getAllInscriptions());

        ServiceCertificat serviceCertif= new ServiceCertificat();

        Certifs cer1 = new Certifs("Python Fondamentaux");
        serviceCertif.addCertif(cer1);
        System.out.println(serviceCertif.getAllCertifs());

        /*  try {
            sc.delete(4);
            //System.out.println("Cours updated successfully!");
            System.out.println("Cours deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }*/

      /* UserService us = new UserService();

        try {
            us.ajouter(new User("chayma", "samaali", "hjr"));

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        /*RoleService rs = new RoleService();

        try {
            rs.ajouter(new Role(6,"etudiant"));

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }*/


        ServiceQuizz sq = new ServiceQuizz();
        try{

            sq.addQuizz(new Quizz("abeerrrr","cours", new Date(2002,12,23).toLocalDate()));}
        catch (SQLException e){System.out.println(e.getMessage());}

        /*QuizzCatService sq = new QuizzCatService();
        try{

            sq.addQuizz(new QuizzCat("ali","rrrrr"));}
        catch (SQLException e){System.out.println(e.getMessage());}*/


    }



}