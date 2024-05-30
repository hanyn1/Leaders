package Services;

import Interfaces.workInterface;
import Models.Article;
import Models.Cours;
import utils.MyConfig;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCours implements workInterface<Cours> {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;
    public  ServiceCours(){
        this.connection= this.instance.getConnection();
        System.out.println("service");
    }


    @Override
    public void add(Cours cours) {
        String req = "INSERT INTO `cours`(`titre`, `description`, `video`) VALUES ('"+cours.getTitre()+"','"+cours.getDescription()+"','"+cours.getVideo()+"')";
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
        List<Cours> cours = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            String req= "SELECT * FROM cours where id=1";
            ResultSet res=st.executeQuery(req);
            while(res.next()){
                Cours c =new Cours();
                c.setId(res.getInt("id"));
                c.setTitre(res.getString(2));
                c.setDescription(res.getString(3));
                c.setVideo(res.getString(4));


                cours.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cours;
    }

    @Override
    public void update(Cours cours) throws SQLException {
        String req = "UPDATE `cours` SET `titre`=?, `description`=?, `video`=? WHERE id=?";
        PreparedStatement ps = this.connection.prepareStatement(req);
        ps.setString(1,cours.getTitre());
        ps.setString(2,cours.getDescription());
        ps.setString(3,cours.getVideo());
        ps.setInt(4,cours.getId());
        ps.executeUpdate();


    }

    @Override
    public void delete(Cours cours) {

    }
}

