package DatabaseUtility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String DB_NAME = "employee.sqlite";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    private Connection connection;

    public boolean getConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;

        } catch(SQLException e) {
            System.out.println("Couldn't connect to the database." + e.getMessage());
            return false;
        }
    }






















}
