package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConfig {
    static final String url="jdbc:mysql://localhost:3306/evolearn";
    static final String user="root";
    static final String pass="";
    private Connection connection;
    static MyConfig instance;

    private MyConfig() {
        try {
            connection = DriverManager.getConnection(url, user, pass);

            System.out.println("connection etablie!");
        } catch (SQLException q) {
            q.printStackTrace();
            //System.out.println(q.getMessage());
        }
    }
    public static MyConfig getInstance()
    {
        if(instance==null)
        {
            instance= new MyConfig();
        }
        return instance;
    }
    public Connection getConnection(){
        return this.connection;
    }
}
