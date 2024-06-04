package Models;

public class User {

    //Variables
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    private String role;

    //Constructeur
    public User(){
    }

    public User(int id, String nom, String email, String motDePasse, String role){
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;

    }
    public User(String nom, String email, String motDePasse,String role){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getRole() { return role; }

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
    public void setRole(String role) { this.role = role; }
}