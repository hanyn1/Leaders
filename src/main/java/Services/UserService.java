package Services;

import Interfaces.IUserInterface;
import Models.User;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserInterface<User> {
    private Connection connection;

    public UserService(){
        connection = MyConfig.getInstance().getConnection();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String ajouter = "INSERT INTO utilisateurs (nom,email,motDePasse) VALUES('"+user.getNom()+"','"+user.getEmail()+"','"+user.getMotDePasse()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(ajouter);

    }

    @Override
    public void modifier(User user) throws SQLException {
        String modifier= "UPDATE utilisateurs SET nom = ?, email = ?, motDePasse = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(modifier);
        us.setString(1, user.getNom());
        us.setString(2, user.getEmail());
        us.setString(3, user.getMotDePasse());
        us.setInt(4, user.getId());

        us.executeUpdate();


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String supprimer = "DELETE FROM utilisateurs WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(supprimer);
        us.setInt(1,id);
        us.executeUpdate();

    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM utilisateur";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setEmail(rs.getString("email"));
            user.setMotDePasse(rs.getString("motDePasse"));
            user.setRoleId(rs.getInt("roleid"));

            utilisateurs.add(user);
        }
        return utilisateurs;
    }

}
