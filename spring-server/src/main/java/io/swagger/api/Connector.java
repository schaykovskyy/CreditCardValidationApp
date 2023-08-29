package io.swagger.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {
    private String dbUrl = "jdbc:mysql://localhost:3306/cards-db";
    private String username= "root";
    private String password= "pass";

    private static Connector instance;

    private Connection conn;

    private Connector() {
        this.conn = null;
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   
            // this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/animalsDB","testUser","test");
            this.conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return this.conn;
    }
}