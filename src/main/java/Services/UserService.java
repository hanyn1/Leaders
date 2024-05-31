package Services;

import Interfaces.IUserInterface;
import Models.User;
import UTILS.MyConfig;

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
        String req = "INSERT INTO utilisateur (id,nom,email,motDePasse) VALUES('"+user.getId()+"','"+user.getNom()+"','"+user.getEmail()+"','"+user.getMotDePasse()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(User user) throws SQLException {
        String req= "UPDATE utilisateur SET nom = ?, email = ?, motDePasse = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setString(1, user.getNom());
        us.setString(2, user.getEmail());
        us.setString(3, user.getMotDePasse());
        us.setInt(4, user.getId());
        us.executeUpdate();


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1,id);
        us.executeUpdate();

    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> utilisateur = new ArrayList<>();
        String req = "SELECT * FROM utilisateur";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setEmail(rs.getString("email"));
            user.setMotDePasse(rs.getString("motDePasse"));

            utilisateur.add(user);
        }
        return utilisateur;
    }

}
