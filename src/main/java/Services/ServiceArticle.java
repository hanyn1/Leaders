package Services;

import Interfaces.workInterface;
import Models.Article;
import utils.MyConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceArticle implements workInterface<Article> {

    MyConfig instance= MyConfig.getInstance();
    Connection connection;
    public ServiceArticle(){

        this.connection= this.instance.getConnection();
        System.out.println("service");
    }

    @Override
    public void add(Article a) {
        //String req= "INSERT INTO '"
        String req="INSERT INTO article(titre, description, contenu, datePublication) VALUES('"+a.getTitre()+"' ,'" +a.getDescription()+"' ,'" +a.getContenu()+"' ,'" +a.getDatePublication()+"')";

        try {
            Statement st= connection.createStatement();
            st.executeUpdate(req);
            System.out.println("article ajouté avec succé");
        }catch (SQLException var)
        {
            var.printStackTrace();
        }
    }

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList();
        try {
            Statement st=this.connection.createStatement();
            String req="SELECT * FROM article ";
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                articles.add(new Article(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6) ));
            }
        }catch (SQLException var1){
            var1.printStackTrace();
        }

        return articles;
    }
    @Override
    public List<Article> getByIndex() {
        List<Article> articles = new ArrayList();
        try {
            Statement st=this.connection.createStatement();
            String req="SELECT * FROM article where id=2 ";
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                articles.add(new Article(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6) ));
            }
        }catch (SQLException var1){
            var1.printStackTrace();
        }

        return articles;
    }
    @Override
    public void updateArticle(Article article) throws SQLException {
        String query = "UPDATE Article SET titre = ?, description = ?, contenu = ?, datePublication = ? WHERE id = ?";

        PreparedStatement ps = this.connection.prepareStatement(query);
        ps.setInt(1, article.getId());
        ps.setString(2, article.getTitre());
        ps.setString(3, article.getDescription());
        ps.setString(4, article.getContenu());
        ps.setDate(5, new java.sql.Date(article.getDatePublication().getTime()));
        ps.executeUpdate();
    }
}





