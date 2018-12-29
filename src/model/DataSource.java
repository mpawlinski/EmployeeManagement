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

    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET id = ?, firstname = ?, lastname = ?, email = ? WHERE id = ? AND firstname = ? AND lastname = ?";
    public static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ? AND firstname = ? AND lastname = ?";
    public static final String QUERY_IF_EXISTS = "SELECT * FROM employees WHERE id = ? AND firstname = ? AND lastname = ? AND email = ?";

    private Connection connection;

    private PreparedStatement listEmployees;
    private PreparedStatement insertIntoEmployees;
    private PreparedStatement updateEmployee;
    private PreparedStatement deleteEmployee;
    private PreparedStatement queryEmployee;

    public boolean open() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            listEmployees = connection.prepareStatement(QUERY_EMPLOYEES);
            insertIntoEmployees = connection.prepareStatement(INSERT_EMPLOYEE);
            updateEmployee = connection.prepareStatement(UPDATE_EMPLOYEE);
            deleteEmployee = connection.prepareStatement(DELETE_EMPLOYEE);
            queryEmployee = connection.prepareStatement(QUERY_IF_EXISTS);
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database. " + e.getMessage());
            return false;
        }
    }

    public void close() {

        try {

            if (listEmployees != null) {
                listEmployees.close();
            }

            if (insertIntoEmployees != null) {
                insertIntoEmployees.close();
            }

            if (updateEmployee != null) {
                updateEmployee.close();
            }

            if(deleteEmployee != null) {
                deleteEmployee.close();
            }

            if(queryEmployee != null) {
                queryEmployee.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close the database. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    //singleton
    private static DataSource instance = new DataSource();

    private DataSource() {

    }

    public static DataSource getInstance() {
        return instance;
    }

    public List<Employee> queryEmployees() {

        try {
            ResultSet results = listEmployees.executeQuery();

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

    public void addEmployee(String id, String firstName, String lastName, String email, String dob) {

        try {
            insertIntoEmployees.setString(1, id);
            insertIntoEmployees.setString(2, firstName);
            insertIntoEmployees.setString(3, lastName);
            insertIntoEmployees.setString(4, email);
            insertIntoEmployees.setString(5, dob);

            int affectedRows = insertIntoEmployees.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Adding employee failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateEmployee(String updatedId, String updatedFirstName, String updatedLastName, String updatedEmail,
                               String whereId, String whereFirstName, String whereLastName) {

        try {
            updateEmployee.setString(1, updatedId);
            updateEmployee.setString(2, updatedFirstName);
            updateEmployee.setString(3, updatedLastName);
            updateEmployee.setString(4, updatedEmail);
            updateEmployee.setString(5, whereId);
            updateEmployee.setString(6, whereFirstName);
            updateEmployee.setString(7, whereLastName);

            int affectedRows = updateEmployee.executeUpdate();
            if(affectedRows != 1) {
                System.out.println("Updating employee failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String selectedId, String selectedFirstName, String selectedLastName) {

        try {
            deleteEmployee.setString(1, selectedId);
            deleteEmployee.setString(2, selectedFirstName);
            deleteEmployee.setString(3, selectedLastName);

            int affectedRows = deleteEmployee.executeUpdate();
            if(affectedRows != 1) {
                System.out.println("SQL DELETE failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean doesAlreadyExists(String id, String firstName, String lastName, String email) {
        try {

            queryEmployee.setString(1, id);
            queryEmployee.setString(2, firstName);
            queryEmployee.setString(3, lastName);
            queryEmployee.setString(4, email);

            ResultSet results = queryEmployee.executeQuery();

            if(results.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return false;
    }



















}
