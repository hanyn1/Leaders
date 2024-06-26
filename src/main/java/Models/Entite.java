package Models;

public class Entite {
    private int id;
    private String titre;
    private String description;

    // Constructors, getters, and setters
    public Entite() {}
    public Entite(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }
    public Entite( String titre, String description) {

        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Entite{" +
                "id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
