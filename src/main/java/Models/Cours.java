package Models;

public class Cours extends Entite {
    // Constructors
    private String video;
    private String image;
    public Cours(){}
    public Cours(String titre, String description, String video,String image) {
        super(titre, description);
        this.video = video;
        this.image = image;
    }
    public Cours(int id, String titre, String description, String video,String image) {
        super(id, titre, description);
        this.video = video;
        this.image = image;
    }

    // getters, and setters
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void ajouterEvaluation() {

    }

    @Override
    public String toString() {
        return "Cours{" +
                "video='" + video + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
