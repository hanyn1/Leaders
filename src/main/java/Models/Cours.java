package Models;

public class Cours extends Entite {
    // Constructors
    private String video;
    public Cours(){}
    public Cours(String titre, String description, String video) {
        super(titre, description);
        this.video = video;
    }
    public Cours(int id, String titre, String description, String video) {
        super(id, titre, description);
        this.video = video;
    }

    // getters, and setters
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    public void ajouterEvaluation() {

    }

    @Override
    public String toString() {
        return "Cours{" +
                "video='" + video + '\'' +
                '}';
    }
}
