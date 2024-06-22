package Models;

import java.sql.Date;

public class Quizz {
    private int id;
    private String titre;
    private String description;
    private Date date;

    // Constructor
    public Quizz(int id, String titre, String description, Date date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    public Quizz(String titre, String description, Date date) {
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    // Default Constructor
    public Quizz() {
    }

    // Getters and Setters
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // toString method
    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
