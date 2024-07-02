package Models;

import java.util.HashSet;
import java.util.Set;

public class User {

    //Variables
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    private String roleName;

    private int roleId;

    //Constructeur
    public User(){
    }

    public User(String roleName) {
        this.roleName = roleName;
    }

    public User(int id, String nom, String email, String motDePasse, int roleId){
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.roleId = roleId;

    }

    public User(String nom, String email, String motDePasse){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public User(int id, String nom, String email, String motDePasse, String roleName, int roleId) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.roleName = roleName;
        this.roleId = roleId;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public int getRoleId() {return roleId;}

    public String getRoleName() {
        return roleName;
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

    public void setRoleId(int roleId) {this.roleId = roleId;}

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}