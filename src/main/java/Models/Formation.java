package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Formation {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty titre = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();

    public Formation() {
        // Default constructor
    }

    public Formation(String titre, String description) {
        this.titre.set(titre);
        this.description.set(description);
    }

    // Getters and setters for properties

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitre() {
        return titre.get();
    }

    public SimpleStringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id.get() +
                ", titre='" + titre.get() + '\'' +
                ", description='" + description.get() + '\'' +
                '}';
    }
}
