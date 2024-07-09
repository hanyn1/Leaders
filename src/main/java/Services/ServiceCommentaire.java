package Services;
import Interfaces.CommentaireInterface;
import Models.Article;
import Models.Commentaire;
import Models.User;
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
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
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
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString(2), rs.getTimestamp(6)));
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



    public void add(Article a,Commentaire c, User u) throws SQLException {
        //String req= "INSERT INTO '"
        String req = "INSERT INTO commentaire(contenu, date_creation, article_id, utilisateur_id) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(req)) {;
            stmt.setString(1, c.getContenu());
            stmt.setTimestamp(2, c.getDatepublication());
            stmt.setLong(3, a.getId());
            stmt.setInt(4, u.getId());
            stmt.executeUpdate();
        }
    }

}

