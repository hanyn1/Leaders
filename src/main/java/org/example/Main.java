package org.example;

import Models.Article;
import Models.Cours;
import Models.Quizz;
import Services.ServiceArticle;
import Services.ServiceCours;
import Services.ServiceQuizz;
import utils.MyConfig;
import java.sql.Date;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static <QuizzCategory> void main(String[] args) throws SQLException {

        MyConfig m= MyConfig.getInstance();
        System.out.printf("Hello and welcome!");

        ServiceArticle sa = new ServiceArticle();
        ServiceQuizz sq = new ServiceQuizz();
        Date datequizz = new Date(2024, 12, 30);
        Quizz q1= new Quizz(3,"prog","Skill s on html",datequizz);
        Article a1= new Article();
        // Article a1 = new Article(new Date(2002,12,23),"objet","produit1");
        // sa.add(a1);
        // sq.addQuizz(q1);
         sq.updateQuizz(q1);
        ServiceCours sc = new ServiceCours();
        // Creating a Cours object without id
        Cours c1 = new Cours(1,"Linux", "Deep dive into Linux", "advanced_linux.mp4");
        Cours c2 = new Cours(1,"Devops", "Deep dive into Devops", "advanced_devops.mp4");
        System.out.println(sc.getAll());
        Date datePublication = new Date(2024, 12, 30);
        ServiceArticle s1= new ServiceArticle();

        s1.updateArticle(new Article(1,"nouveau titre","nouvelle description","nouveau contenu",datePublication));
        System.out.println("article updated successfully.");


        //s1.upd(new Article(1,"nouveau titre","nouvelle description","nouveau contenu",datePublication));
       // System.out.println("article updated successfully.");
        Date newdate  = new Date(2024, 12, 02);
        sq.updateQuizz(new Quizz(1245,"new title","new description",newdate));
        // sc.add(c1);
       // sc.add(c2);
        //sc.update(c1);

        try {
            sc.delete(1);
            System.out.println("Cours deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sc.delete(7);
            System.out.println("QUIZZ deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sc.delete(7);
            System.out.println("CATEGORY deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }



}