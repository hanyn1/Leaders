package Models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evenement {
    private final LongProperty id;
    private final StringProperty titre;
    private final StringProperty description;

    // Constructeur par défaut
    public Evenement() {
        this.id = new SimpleLongProperty();
        this.titre = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
    }

    // Constructeur avec tous les attributs
    public Evenement(long id, String titre, String description) {
        this.id = new SimpleLongProperty(id);
        this.titre = new SimpleStringProperty(titre);
        this.description = new SimpleStringProperty(description);
    }

    public Evenement(String titre, String description) {
        this.id = new SimpleLongProperty();
        this.titre = new SimpleStringProperty(titre);
        this.description = new SimpleStringProperty(description);
    }

    // Getters et setters
    public int getId() {
        return (int) id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getTitre() {
        return titre.get();
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public StringProperty titreProperty() {
        return titre;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    // Méthode toString pour afficher les informations de l'objet
    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id.get() +
                ", titre='" + titre.get() + '\'' +
                ", description='" + description.get() + '\'' +
                '}';
    }
}
