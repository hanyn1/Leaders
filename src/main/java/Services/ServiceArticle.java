package Services;

import Interfaces.workInterface;
import Models.Article;
import UTILS.MyConfig;

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

            String req="INSERT INTO 'Article'(date, Objet,Description)"+" VALUES('"+ a.getDate()+"','"+ a.getObjet()+"' ,'" + a.getDescription()+"')";

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
            List<Article> articles= new ArrayList();
            try {
                Statement st=this.connection.createStatement();
                String req="SELECT * FROM Articles";
                ResultSet rs=st.executeQuery(req);
                while(rs.next()){
                    articles.add(new Article(rs.getInt(1),rs.getDate("Date"), rs.getString("Objet"), rs.getString("Description")));
                }
            }catch (SQLException var1){
                var1.printStackTrace();
            }

            return articles;
    }
}
