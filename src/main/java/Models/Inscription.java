package Models;

import java.time.LocalDate;
import java.util.Date;

public class Inscription {
    private int id;
    private Date date_creation;
    private int coursId;
    private int utilisateurId;
    // Constructeurs
    public Inscription() {}

    public Inscription(int id, Date  date_creation, int coursId, int utilisateurId) {
        this.id = id;
        this.date_creation = date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public Inscription(Date  date_creation, int coursId, int utilisateurId) {
        this. date_creation=  date_creation;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public Inscription( int coursId, int utilisateurId) {
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
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

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", date_creation=" + date_creation +
                ", coursId=" + coursId +
                ", utilisateurId=" + utilisateurId +
                '}';
    }
}
