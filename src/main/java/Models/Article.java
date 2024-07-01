package Models;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Article {
    private int id;
    private String titre;
    private String description;
    private String contenu;
    private Timestamp dateCreation;


    public Article(int id, String titre, String description, String contenu, Timestamp dateCreation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
    }

    public Article(String titre, String description, String contenu, Timestamp dateCreation) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
    }

    public Article() {

    }

    public Article(String text, String text1, String text2, LocalDate localDate) {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }


}
