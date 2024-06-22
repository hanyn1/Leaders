package Models;

import java.sql.Date;

public class QuizzCat {
    private int id;
    private String nom;
    private String description;

    // Constructor
    public QuizzCat(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;}

    public QuizzCat(String titre, String description) {
        this.nom = titre;
        this.description = description;}

    // Default Constructor
    public QuizzCat() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method

    @Override
    public String toString() {
        return "QuizzCat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
