package Models;

public class Role {

    //variables
    private int id;
    private String name;

    //Constructeur

    public Role(){
    }
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String bio, String photo) {
        this.name = name;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
