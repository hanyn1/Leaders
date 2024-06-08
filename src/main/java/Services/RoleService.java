package Services;

import Interfaces.IRoleInterface;
import Models.Role;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleService implements IRoleInterface <Role> {

    private Connection connection;

    public RoleService(){
        connection = MyConfig.getInstance().getConnection();
    }


    @Override
    public void ajouter(Role role) throws SQLException {
        String req = "INSERT INTO roles (id,name) VALUES('"+role.getId()+"','"+role.getName()+"') ";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(Role role) throws SQLException {
        String req= "UPDATE roles SET name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, role.getName());

        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM roles WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1,id);
        us.executeUpdate();

    }

    @Override
    public List<Role> recuperer() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String req = "SELECT * FROM role";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));

            roles.add(role);
        }
        return roles;
    }
}
