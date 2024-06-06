package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Quizz extends QuizzCategorie {
    private int id;
    private String titre;
    private String description;


    // Constructor


    public Quizz(int id, String titre, String description) throws SQLException {
        super(id, titre, description);
        this.id = id;
        this.titre = titre;
        this.description = description;
        ;
    }

    public Quizz(int id, String prog, String skillSOnHtml, Date datequizz) {
        super(id, null, null);

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // toString method
    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    // Method to retrieve quiz date from the database
    public CallableStatement getQuizzDate(Connection connection) throws SQLException {
        String sql = "{call get_quizz_date(?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, this.id);
        return callableStatement;
    }
