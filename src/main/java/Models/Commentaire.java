package Models;

import java.util.Date;

public class Commentaire {
    private int id;
    private String contenu;
    private Date datepublication;


    // Constructors, getters, and setters

    public Commentaire() {
    }

    public Commentaire(int id) {
        this.id = id;
    }

    public Commentaire(int id, String contenu, Date datepublication) {
        this.id = id;
        this.contenu = contenu;
        this.datepublication = datepublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Date datepublication) {
        this.datepublication = datepublication;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", datepublication=" + datepublication +
                '}';
    }
}
