package Services;

import Interfaces.inscriptionInterface;
import Models.Inscription;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscription implements inscriptionInterface<Inscription> {
    MyConfig instance= MyConfig.getInstance();
    Connection connection;

    public ServiceInscription(){
        this.connection= this.instance.getConnection();
        System.out.println("ServiceInscription is connected to database");
    }

    @Override
    public void addInscription(Inscription inscription) throws SQLException {
    String req = "INSERT INTO inscriptions (cours_id, utilisateur_id) VALUES ('"+inscription.getCoursId()+"', '"+inscription.getUtilisateurId()+"')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("enrolling is done");
    }

    @Override
    public List<Inscription> getAllInscriptions() {
        List<Inscription> ins = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            String req= "SELECT * FROM inscriptions";
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Inscription i = new Inscription();
                i.setId(res.getInt("id"));
                i.setDate_creation(res.getDate(2));
                i.setCoursId(res.getInt(3));
                i.setUtilisateurId(res.getInt(4));

                ins.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ins;
    }

    @Override
    public Inscription getInscriptionById(Inscription inscription) {


        try  {
            Statement statement = this.connection.createStatement();
            String req = "SELECT * FROM inscriptions WHERE id = ?";
            ResultSet resultSet = statement.executeQuery(req);
            if (resultSet.next()) {
                Inscription i = new Inscription();
                i.setId(resultSet.getInt("id"));
                i.setDate_creation(resultSet.getDate("date_creation"));
                i.setCoursId(resultSet.getInt("cours_id"));
                i.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
