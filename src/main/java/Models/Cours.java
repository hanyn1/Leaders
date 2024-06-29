package Models;

public class Cours extends Entite {
    // Constructors
    private String video;
    private String image;
    private Float price;
    public Cours(){}

    public Cours(int id, String titre, String description, String video, String image, Float price) {
        super(id, titre, description);
        this.video = video;
        this.image = image;
        this.price = price;
    }

    public Cours(String titre, String description, String video, String image, Float price) {
        super(titre, description);
        this.video = video;
        this.image = image;
        this.price = price;
    }

    // getters, and setters
    public String getVideo() {
        return video;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
                ", price=" + price +
                '}';
    }
}
