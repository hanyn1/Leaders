package Models;

public class RessourceEv {
    private long id;
    private String titre;
    private String url;
    private String type;
    private long evenement_id;

    // Constructeur par défaut
    public RessourceEv() {
    }

    // Constructeur avec tous les attributs
    public RessourceEv(long id, String titre, String url, String type, long evenement_id) {
        this.id = id;
        this.titre = titre;
        this.url = url;
        this.type = type;
        this.evenement_id = evenement_id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEvenementId() {
        return evenement_id;
    }

    public void setEvenementId(long evenement_id) {
        this.evenement_id = evenement_id;
    }

    // Méthode toString pour afficher les informations de l'objet
    @Override
    public String toString() {
        return "RessourceEv{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", evenement_id=" + evenement_id +
                '}';
    }
}

