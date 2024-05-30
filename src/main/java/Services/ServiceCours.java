package Services;

import Interfaces.workInterface;
import Models.Cours;
import utils.MyConfig;

import java.sql.Connection;
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

