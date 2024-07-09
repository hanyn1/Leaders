//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Models;

import org.controlsfx.control.Rating;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Article {
    private int id;
    private String titre;
    private String description;
    private String contenu;
    private Double rating;
    private List<Integer> ratings; // Liste pour stocker les évaluations

    private Timestamp datePublication;
    private double averageRating;
    private int ratingsCount;//Nombre d'évaluations
    public Article() {
        this.averageRating = 0.0; // Initialiser la note moyenne à 0
        this.ratingsCount = 0; // Initialiser le nombre d'évaluations à 0

    }

    public Article(String titre, String description, String contenu, double rating) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.rating=rating;
        this.averageRating = 0.0; // Initialiser la note moyenne à 0
        this.ratingsCount = 0; // Initialiser le nombre d'évaluations à 0
    }

    public Article(int id, String titre, String description, String contenu, Timestamp datePublication) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.averageRating = 0.0; // Initialiser la note moyenne à 0
        this.ratingsCount = 0; // Initialiser le nombre d'évaluations à 0

    }


    // Méthode pour ajouter une nouvelle note à l'article
    public void addRating(int rating) {
        // Calcul de la nouvelle note moyenne en tenant compte de la moyenne actuelle et de la nouvelle évaluation
        double totalRating = this.averageRating * this.ratingsCount;
        totalRating += rating;
        this.ratingsCount++;
        this.averageRating = totalRating / this.ratingsCount;
    }

    // Méthode pour obtenir le nombre de notations reçues
    public int ratingsCount() {
        // Pour simplifier, on peut imaginer que chaque note correspond à un rating.
        return (int) Math.round(this.averageRating);
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenu() {
        return this.contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDatePublication() {
        return this.datePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        this.datePublication = datePublication;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setDateCreation(Timestamp dateCreation) {
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", content='" + contenu + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double newRating) {
    }
}
