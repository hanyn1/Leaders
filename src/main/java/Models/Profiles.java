package Models;

public class Profiles {
    //variables
    private int id;
    private String bio;
    private String photo;

    //Constructeur

    public Profiles(){
    }
    public Profiles(int id, String bio, String photo) {
        this.id = id;
        this.bio = bio;
        this.photo = photo;
    }

    public Profiles(String bio, String photo) {
        this.bio = bio;
        this.photo = photo;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoto() {
        return photo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Profiles{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
