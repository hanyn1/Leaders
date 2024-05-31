package Models;

import java.sql.Date;

public class Article {
    private int id;
    private String titre;
    private String description;
    private String contenu;
    private Date datePublication;

    public Article() {

    }

    public Article(String titre, String description, String contenu, Date datePublication) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }
    public Article(int id, String titre, String description, String contenu, Date datePublication) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "article{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", contenu='" + contenu + '\'' +
                ", datePublication=" + datePublication +
                '}';
    }}
