package Models;

public class User {

    //Variables
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    private int roleId;

    //Constructeur
    public User(){
    }

    public User(int id, String nom, String email, String motDePasse, int roleId){
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.roleId = roleId;

    }
    public User(String nom, String email, String motDePasse,int roleId){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.roleId = roleId;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public int getRoleId() {return roleId;}

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

    public void setRoleId(int roleId) {this.roleId = roleId;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}