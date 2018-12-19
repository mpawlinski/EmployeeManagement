package loginapp;

import DatabaseUtility.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public static final String LOGIN_QUERY = "SELECT * FROM login WHERE username = ? AND password = ? AND status = ?";

    public LoginModel() {
        this.connection = DatabaseConnection.getConnection();
        if(this.connection == null) {
            System.exit(-1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public boolean isLoggedIn(String username, String password, String status) {
        ResultSet results = null;
        PreparedStatement queryLogin = null;

        try {
            queryLogin = connection.prepareStatement(LOGIN_QUERY);
            queryLogin.setString(1, username);
            queryLogin.setString(2, password);
            queryLogin.setString(3, status);
            results = queryLogin.executeQuery();
            if(results.next()) {
                return true;
            } else {
                return false;
            }

        } catch(SQLException e) {
            System.out.println("Login error! " + e.getMessage());
            return false;
        } finally {
            try {

                if (queryLogin != null) {
                    queryLogin.close();
                }

                if(results != null) {
                    results.close();
                }

//                if(connection != null) {
//                    connection.close();
//                }
            } catch (SQLException e) {
                System.out.println("Error closing database. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
