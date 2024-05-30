package Models;

public class Categorie {
    private String id;
    private String nom;
    private Categorie parent;

    // Constructors, getters, and setters
    public Categorie(String id, String nom, Categorie parent) {
        this.id = id;
        this.nom = nom;
        this.parent = parent;
    }
}
