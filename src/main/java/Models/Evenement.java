package Models;

public class Evenement {
    private long id;
    private String titre;
    private String description;

    // Constructeur par défaut
    public Evenement() {
    }

    // Constructeur avec tous les attributs
    public Evenement(long id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    // Méthode toString pour afficher les informations de l'objet
    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}