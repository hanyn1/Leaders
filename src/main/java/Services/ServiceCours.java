package Services;

import Interfaces.coursInterface;
import Models.Cours;
import com.cloudinary.Cloudinary;
import utils.CloudinaryConfig;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCours implements coursInterface<Cours> {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;
    private Cloudinary cloudinary;
    public  ServiceCours(){
        this.connection= this.instance.getConnection();
        System.out.println("service");
        this.cloudinary= CloudinaryConfig.getCloudinary();
    }


    @Override
    public void add(Cours cours) {
        String req = "INSERT INTO `cours`(`titre`, `description`, `video`, `image`) VALUES ('"+cours.getTitre()+"','"+cours.getDescription()+"','"+cours.getVideo()+"','"+cours.getImage()+"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Cours added!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cours> getAll() {
        List<Cours> crs = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            String req= "SELECT * FROM cours";
            ResultSet res=st.executeQuery(req);
            while(res.next()){
                Cours c =new Cours();
                c.setId(res.getInt("id"));
                c.setTitre(res.getString(2));
                c.setDescription(res.getString(3));
                c.setVideo(res.getString(4));
                c.setImage(res.getString(5));


                crs.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return crs;
    }

    @Override
    public void update(Cours cours) throws SQLException {
        String req = "UPDATE `cours` SET `titre`=?, `description`=?, `video`,`image`=? WHERE id=?";
        PreparedStatement ps = this.connection.prepareStatement(req);
        ps.setString(1,cours.getTitre());
        ps.setString(2,cours.getDescription());
        ps.setString(3,cours.getVideo());
        ps.setString(4,cours.getImage());
        ps.setInt(5,cours.getId());
        ps.executeUpdate();


    }

    @Override
    public void delete(Cours cours) {

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `cours` WHERE id=?";
        PreparedStatement ps = this.connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();

    }
}

