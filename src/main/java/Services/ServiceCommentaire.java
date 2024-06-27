package Services;
import Interfaces.CommentaireInterface;
import Models.Commentaire;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommentaire implements CommentaireInterface<Commentaire> {
    MyConfig instance = MyConfig.getInstance();
    Connection connection;

    public ServiceCommentaire() {

        this.connection = this.instance.getConnection();
        System.out.println("service Commentaire");
    }


    @Override
    public void add(Commentaire c) {
        //String req= "INSERT INTO '"
        String req = "INSERT INTO Commentaire(contenue, datepublication) VALUES('" + c.getContenu() + "' ,'" + c.getDatepublication() + "')";

        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire ajouté avec succé");
        } catch (SQLException var) {
            var.printStackTrace();
        }
    }


    public List<Commentaire> getAll() {
        List<Commentaire> commentaires = new ArrayList();
        try {
            Statement st = this.connection.createStatement();
            String req = "SELECT * FROM commentaire ";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString(2), rs.getDate(3)));
            }
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

        return commentaires;
    }

    @Override
    public List<Commentaire> getByIndex() {
        List<Commentaire> commentaires = new ArrayList();
        try {
            Statement st = this.connection.createStatement();
            String req = "SELECT * FROM commentaire where id=2 ";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString(2), rs.getDate(6)));
            }
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

        return commentaires;
    }

    public void updateCommentaire(Commentaire commentaire) throws SQLException {
        String query = "UPDATE Commentaire SET contenue = ?, datepublication = ? WHERE id = ?";

        PreparedStatement ps = this.connection.prepareStatement(query);
        ps.setInt(1, commentaire.getId());
        ps.setString(2, commentaire.getContenu());
        ps.setDate(3, new Date(commentaire.getDatepublication().getTime()));
        ps.executeUpdate();
    }
}

