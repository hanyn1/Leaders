package Models;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Temoignage {
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty utilisateurId = new SimpleIntegerProperty();
    private StringProperty contenu = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> dateCreation = new SimpleObjectProperty<>();

    // Constructors
    public Temoignage() {}

    public Temoignage(int utilisateurId, String contenu) {
        this.utilisateurId.set(utilisateurId);
        this.contenu.set(contenu);
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUtilisateurId() {
        return utilisateurId.get();
    }

    public IntegerProperty utilisateurIdProperty() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId.set(utilisateurId);
    }

    public String getContenu() {
        return contenu.get();
    }

    public StringProperty contenuProperty() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu.set(contenu);
    }

    public LocalDateTime getDateCreation() {
        return dateCreation.get();
    }

    public ObjectProperty<LocalDateTime> dateCreationProperty() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation.set(dateCreation);
    }
}
