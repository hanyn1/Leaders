package org.example;
import utils.MyConfig;
import java.sql.SQLException;
import Services.ServiceTemoignage;
import Services.UserService;
import Models.Temoignage;


public class Main {
    public static void main(String[] args) {
        try {
            MyConfig config = MyConfig.getInstance();

            ServiceTemoignage serviceTemoignage = new ServiceTemoignage(config);

            // Now you can use serviceTemoignage object for further operations

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}