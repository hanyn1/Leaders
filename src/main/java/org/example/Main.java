package org.example;

import Models.Article;
import Models.Cours;
import Services.ServiceArticle;
import Services.ServiceCours;
import utils.MyConfig;

import java.sql.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MyConfig m= MyConfig.getInstance();
       System.out.printf("Hello and welcome!");
       ServiceArticle sa = new ServiceArticle();
       ServiceCours sc = new ServiceCours();
       Article a1 = new Article(new Date(2002,12,23),"objet","produit1");
       sa.add(a1);

        // Creating a Cours object without id
        Cours c1 = new Cours("Advanced Java", "Deep dive into Java", "advanced_java.mp4");
        sc.add(c1);
    }
}