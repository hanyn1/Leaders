package org.example;

import Models.Article;
import Models.Cours;
import Services.ServiceArticle;
import Services.ServiceCours;
import utils.MyConfig;

import java.sql.Date;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        MyConfig m= MyConfig.getInstance();
        System.out.printf("Hello and welcome!");
//ServiceArticle sa = new ServiceArticle();
        ServiceCours sc = new ServiceCours();
        // Article a1 = new Article(new Date(2002,12,23),"objet","produit1");
        // sa.add(a1);

        // Creating a Cours object without id
        Cours c1 = new Cours(1,"Linux", "Deep dive into Linux", "advanced_linux.mp4");
        Cours c2 = new Cours(1,"Devops", "Deep dive into Devops", "advanced_devops.mp4");
        System.out.println(sc.getAll());
       // sc.add(c1);
       // sc.add(c2);
        //sc.update(c1);
      /*  try {
            sc.delete(4);
            //System.out.println("Cours updated successfully!");
            System.out.println("Cours deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } */
        }



}