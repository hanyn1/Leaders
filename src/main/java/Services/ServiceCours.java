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
        String req = "INSERT INTO `cours`(`id`, `titre`, `description`, `video`) VALUES ('?','?','?','?')";
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
        return null;
    }

    @Override
    public void update(Cours cours) {

    }

    @Override
    public void delete(Cours cours) {

    }
}

