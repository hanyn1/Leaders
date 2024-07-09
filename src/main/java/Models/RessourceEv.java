package Models;

import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class RessourceEv {
    private int id;
    private String titre;
    private String description;
    private String url;
    private Timestamp date_creation;

    // Constructeur pour les données String
    public RessourceEv(String titre, String description, String url, Timestamp date_creation) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.date_creation = date_creation;
    }

    // Constructeur avec ID (pour la récupération depuis la base de données)
    public RessourceEv(int id, String titre, String description, String url, Timestamp date_creation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.date_creation = date_creation;
    }
    // Getters et Setters
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getDateCreation() {
        return date_creation;
    }

    public void setDateCreation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "RessourceEv{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }
}
