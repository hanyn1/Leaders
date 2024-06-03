package Models;

import java.time.LocalDate;

public class Inscription {
    private int id;
    private LocalDate dateInscription;
    private int coursId;
    private int utilisateurId;
    // Constructeurs
    public Inscription() {}

    public Inscription(int id, LocalDate dateInscription, int coursId, int utilisateurId) {
        this.id = id;
        this.dateInscription = dateInscription;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public Inscription(LocalDate dateInscription, int coursId, int utilisateurId) {
        this.dateInscription = dateInscription;
        this.coursId = coursId;
        this.utilisateurId = utilisateurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
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
}
