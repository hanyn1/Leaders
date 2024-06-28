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
        String ajouter = "INSERT INTO utilisateurs (nom,email,motDePasse,role_id) VALUES('"+user.getNom()+"','"+user.getEmail()+"','"+user.getMotDePasse()+"','"+user.getRoleId()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(ajouter);

    }

    @Override
    public void modifier(User user) throws SQLException {
        String modifier= "UPDATE utilisateurs SET nom = ?, email = ?, motDePasse = ?, role_id = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(modifier);
        us.setString(1, user.getNom());
        us.setString(2, user.getEmail());
        us.setString(3, user.getMotDePasse());
        us.setInt(4, user.getId());
        us.setInt(5, user.getRoleId());

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
        String req = "SELECT utilisateurs.id, utilisateurs.nom, utilisateurs.email, utilisateurs.motDePasse, roles.name AS roleName FROM utilisateurs JOIN roles ON utilisateurs.role_id = roles_id";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setEmail(rs.getString("email"));
            user.setMotDePasse(rs.getString("motDePasse"));
            user.setRoleId(rs.getInt("role_id"));
            user.setRoleName(rs.getString("roleName"));

            utilisateurs.add(user);
        }
        return utilisateurs;
    }

}
