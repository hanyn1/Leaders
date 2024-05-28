package Models;

public class Blog {
    private int id;
    String Nom, Prenom;
Article A= new Article();
    public Blog() {
    }

    public Blog(String nom, String prenom) {
        Nom = nom;
        Prenom = prenom;
    }

    public Blog(int id, String nom, String prenom) {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", A=" + A +
                '}';
    }
}
