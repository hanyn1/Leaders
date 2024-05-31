package Models;

public class User {

    //Variables
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    //Constructeur
    public User(){
    }

    public User(int id, String nom, String email, String motDePasse){
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;

    }
    public User(String nom, String email, String motDePasse){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}