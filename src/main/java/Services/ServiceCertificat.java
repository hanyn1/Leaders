package Services;

import Interfaces.certifInterface;
import Models.Certifs;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCertificat implements certifInterface<Certifs> {
    private Connection connection;

    public ServiceCertificat() throws SQLException {
        connection = MyConfig.getInstance().getConnection();
        System.out.println("ServiceCertificate is connected to database");

    }

    @Override
    public void addCertif(Certifs certifs) throws SQLException {
        String req = "INSERT INTO certifs (nom) VALUES ('"+certifs.getNom()+"')";
        try  {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("certif is created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Certifs> getAllCertifs() {

        List<Certifs> certifs = new ArrayList<>();
        try {
            Statement st = this.connection.createStatement();
            String req= "SELECT * FROM certifs";
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Certifs c = new Certifs();
                c.setId(res.getInt("id"));
                c.setNom(res.getString("nom"));

                certifs.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return certifs;
    }

    @Override
    public Certifs getCertifById(Certifs certifs) {
        try  {
            Statement statement = this.connection.createStatement();
            String req = "SELECT * FROM certifs WHERE id = ?";
            ResultSet resultSet = statement.executeQuery(req);
            if (resultSet.next()) {
                Certifs c = new Certifs();
                c.setId(resultSet.getInt("id"));
                c.setNom(resultSet.getString("nom"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

