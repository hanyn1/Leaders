package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Formation {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty titre = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();

    // Default constructor
    public Formation() {
        this(0, "", "");
    }

    // Parameterized constructor
    public Formation(int id, String titre, String description) {
        setId(id);
        setTitre(titre);
        setDescription(description);
    }

    // Getters and setters
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitre() {
        return titre.get();
    }

    public StringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
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
