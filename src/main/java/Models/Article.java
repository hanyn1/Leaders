package Models;

import java.sql.Date;

public class Article {
    private int id;
    private Date date;
    private String Objet;
    private String Description;

    public Article() {

    }

    public Article(int id, Date date, String objet, String description) {
        this.id = id;
        this.date = date;
        Objet = objet;
        Description = description;
    }
    public Article(Date date, String objet, String description) {

        this.date = date;
        Objet = objet;
        Description = description;
    }
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", date=" + date +
                ", Objet='" + Objet + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObjet() {
        return Objet;
    }

    public void setObjet(String objet) {
        Objet = objet;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
