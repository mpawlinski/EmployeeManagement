package DatabaseUtility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String DB_NAME = "employee.sqlite";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            return connection;
        } catch(SQLException e) {
            System.out.println("Error connecting to the database. " + e.getMessage());
            return null;
        }
}




















}
