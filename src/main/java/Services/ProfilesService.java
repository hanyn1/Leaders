package Services;

import Interfaces.IProfilesinterface;
import Models.Profiles;
import Models.User;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilesService implements IProfilesinterface <Profiles>{

    private Connection connection;

    public ProfilesService(){
        connection = MyConfig.getInstance().getConnection();
    }


    @Override
    public void ajouter(Profiles profiles) throws SQLException {
        String req = "INSERT INTO profils (id,bio,photo) VALUES('"+profiles.getId()+"','"+profiles.getBio()+"','"+profiles.getPhoto()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(Profiles profiles) throws SQLException {
        String req= "UPDATE profils SET bio = ?, photo = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, profiles.getBio());
        ps.setString(2, profiles.getPhoto());
        ps.setInt(3, profiles.getId());

        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM profils WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1,id);
        us.executeUpdate();

    }

    @Override
    public List<Profiles> recuperer() throws SQLException {
        List<Profiles> profils = new ArrayList<>();
        String req = "SELECT * FROM profils";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Profiles profiles = new Profiles();
            profiles.setId(rs.getInt("id"));
            profiles.setBio(rs.getString("bio"));
            profiles.setPhoto(rs.getString("photo"));

            profils.add(profiles);
        }
        return profils;
    }
}
