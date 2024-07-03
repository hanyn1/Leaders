package Models;

import java.sql.Timestamp;

public class Inscription {
    private int id;
    private Timestamp date_creation;
    private int coursId;
    private int utilisateurId;
    private Timestamp expirationDate;

    // Constructors
    public Inscription() {}

    public Inscription(int id, int coursId, int utilisateurId, Timestamp expirationDate) {
        this.id = id;
        this.date_creation = date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
        this.expirationDate = expirationDate;
    }

    public Inscription( int coursId, int utilisateurId, Timestamp expirationDate) {
        this.date_creation = date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
        this.expirationDate = expirationDate;
    }

    public Inscription(int id, Timestamp date_creation, int coursId, int utilisateurId, Timestamp expirationDate) {
        this.id = id;
        this.date_creation = date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
        this.expirationDate = expirationDate;
    }

    public Inscription(Timestamp date_creation, int coursId, int utilisateurId, Timestamp expirationDate) {
        this.date_creation = date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
        this.expirationDate = expirationDate;
    }

    public Inscription(int coursId, int utilisateurId) {
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public Inscription(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", date_creation=" + date_creation +
                ", coursId=" + coursId +
                ", utilisateurId=" + utilisateurId +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
