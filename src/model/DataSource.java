package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "employee.sqlite";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\JavaProjects\\EmployeeManagement\\" + DB_NAME;

    public static final String QUERY_EMPLOYEES = "SELECT * FROM employees";
    public static final String INSERT_EMPLOYEE = "INSERT INTO employees(id, firstname, lastname, email, dateofbirth)" +
            " VALUES (?, ?, ?, ?, ?)";

    private Connection connection;

//    private PreparedStatement insertIntoEmployees;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
//            insertIntoEmployees = connection.prepareStatement(INSERT_EMPLOYEE);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to databse. " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }

//            if (insertIntoEmployees != null) {
//                insertIntoEmployees.close();
//            }

        } catch (SQLException e) {
            System.out.println("Couldn't close the database. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

//    public  Connection getConnection() {
//        try {
//            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
//            return connection;
//        } catch (SQLException e) {
//            System.out.println("Error connecting to the database. " + e.getMessage());
//            return null;
//        }
//
//    }

    //singleton
    private static DataSource instance = new DataSource();

    private DataSource() {

    }

    public static DataSource getInstance() {

        return instance;
    }

    public List<Employee> queryEmployees() {

        try(Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(QUERY_EMPLOYEES)) {

            List<Employee> employees = new ArrayList<>();
            while(results.next()) {
                Employee employee = new Employee();

                employee.setId(results.getString(1));
                employee.setFirstName(results.getString(2));
                employee.setLastName(results.getString(3));
                employee.setEmail(results.getString(4));
                employee.setDateOfBirth(results.getString(5));
                employees.add(employee);
            }

            return employees;

        } catch (SQLException e) {
            System.out.println("Query error. " + e.getMessage());
            return null;
        }
    }

    // method to add employee
    // check if it exists, create psfs for that, prepared statement, check for null in close method
    // pass all parameters to check if it already exists in a database by select where = ? ? ? ? ?

























}
