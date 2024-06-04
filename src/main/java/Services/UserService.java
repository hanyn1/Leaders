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
        String req = "INSERT INTO utilisateurs (id,nom,email,motDePasse,role) VALUES('"+user.getId()+"','"+user.getNom()+"','"+user.getEmail()+"','"+user.getMotDePasse()+"','"+user.getRole()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(User user) throws SQLException {
        String req= "UPDATE utilisateurs SET nom = ?, email = ?, motDePasse = ? , role = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setString(1, user.getNom());
        us.setString(2, user.getEmail());
        us.setString(3, user.getMotDePasse());
        us.setInt(4, user.getId());
        us.setString(5, user.getRole());

        us.executeUpdate();


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM utilisateurs WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
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
            user.setRole(rs.getString("role"));

            utilisateurs.add(user);
        }
        return utilisateurs;
    }

}
