package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "employeeData.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:src\\" + DB_NAME;

    public static final String LOGIN_QUERY = "SELECT * FROM users WHERE username = ? AND password = ? AND status = ?";

    public static final String QUERY_USERS = "SELECT * FROM users";

    public static final String INSERT_EMPLOYEE = "INSERT INTO users(username, password, firstname, lastname, email, phonenumber, status)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_EMPLOYEE = "UPDATE users SET username = ?, password = ?, firstname = ?, lastname = ?" +
            ", email = ?, phonenumber = ? WHERE id = ?";

    public static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE id = ?";

    public static final String QUERY_IF_EXISTS = "SELECT * FROM users WHERE username = ? AND firstname = ? AND lastname = ?";

    private static final String FIND_EMPLOYEE_ID_BY_NAME = "SELECT id FROM users WHERE username = ?";

    private static final String QUERY_TODO_ITEMS = "SELECT * FROM todoitems WHERE userid = ?";

    private static final String INSERT_TODO_ITEM = "INSERT into todoitems(userid, title, description) VALUES(?, ?, ?)";

    private Connection connection;

    private PreparedStatement login;
    private PreparedStatement listUsers;
    private PreparedStatement insertIntoUsers;
    private PreparedStatement updateEmployee;
    private PreparedStatement deleteEmployee;
    private PreparedStatement queryUsersIfExists;
    private PreparedStatement findEmployeeId;
    private PreparedStatement findTodoItems;
    private PreparedStatement insertTodoItem;

    public boolean open() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            login = connection.prepareStatement(LOGIN_QUERY);
            listUsers = connection.prepareStatement(QUERY_USERS);
            insertIntoUsers = connection.prepareStatement(INSERT_EMPLOYEE);
            updateEmployee = connection.prepareStatement(UPDATE_EMPLOYEE);
            deleteEmployee = connection.prepareStatement(DELETE_EMPLOYEE);
            queryUsersIfExists = connection.prepareStatement(QUERY_IF_EXISTS);
            findEmployeeId = connection.prepareStatement(FIND_EMPLOYEE_ID_BY_NAME);
            findTodoItems = connection.prepareStatement(QUERY_TODO_ITEMS);
            insertTodoItem = connection.prepareStatement(INSERT_TODO_ITEM);
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database. " + e.getMessage());
            return false;
        }
    }

    public void close() {

        try {

            if (login != null) {
                login.close();
            }

            if (listUsers != null) {
                listUsers.close();
            }

            if (insertIntoUsers != null) {
                insertIntoUsers.close();
            }

            if (updateEmployee != null) {
                updateEmployee.close();
            }

            if (deleteEmployee != null) {
                deleteEmployee.close();
            }

            if (queryUsersIfExists != null) {
                queryUsersIfExists.close();
            }

            if (findEmployeeId != null) {
                findEmployeeId.close();
            }

            if (findTodoItems != null) {
                findTodoItems.close();
            }

            if(insertIntoUsers != null) {
                insertIntoUsers.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close the database. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static DataSource instance = new DataSource();

    private DataSource() {

    }

    public static DataSource getInstance() {
        return instance;
    }

    public boolean isLoggedIn(String username, String password, String status) {

        try {
            login.setString(1, username);
            login.setString(2, password);
            login.setString(3, status);
            ResultSet results = login.executeQuery();
            if(results.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean isDatabaseConnected() {
        return connection != null;
    }

    public List<Employee> queryEmployees() {

        try {
            ResultSet results = listUsers.executeQuery();

            List<Employee> employees = new ArrayList<>();
            while(results.next()) {
                Employee employee = new Employee();

                employee.setId(results.getInt(1));
                employee.setUsername(results.getString(2));
                employee.setPassword(results.getString(3));
                employee.setFirstName(results.getString(4));
                employee.setLastName(results.getString(5));
                employee.setEmail(results.getString(6));
                employee.setPhoneNumber(results.getInt(7));
                employee.setStatus(results.getString(8));
                employees.add(employee);
            }

            return employees;

        } catch (SQLException e) {
            System.out.println("Query error. " + e.getMessage());
            return null;
        }
    }

    public void addEmployee(String username, String password, String firstName, String lastName,
                            String email, int phoneNumber, String status) {

        try {
            insertIntoUsers.setString(1, username);
            insertIntoUsers.setString(2, password);
            insertIntoUsers.setString(3, firstName);
            insertIntoUsers.setString(4, lastName);
            insertIntoUsers.setString(5, email);
            insertIntoUsers.setInt(6, phoneNumber);
            insertIntoUsers.setString(7, status);

            int affectedRows = insertIntoUsers.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Adding employee failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateEmployee(String updatedUsername, String updatedPassword, String updatedFirstName,
                               String updatedLastName, String updatedEmail, int updatedPhoneNumber,
                               int whereId) {

        try {
            updateEmployee.setString(1, updatedUsername);
            updateEmployee.setString(2, updatedPassword);
            updateEmployee.setString(3, updatedFirstName);
            updateEmployee.setString(4, updatedLastName);
            updateEmployee.setString(5, updatedEmail);
            updateEmployee.setInt(6, updatedPhoneNumber);
            updateEmployee.setInt(7, whereId);

            int affectedRows = updateEmployee.executeUpdate();
            if(affectedRows != 1) {
                System.out.println("Updating employee failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int selectedId) {

        try {
            deleteEmployee.setInt(1, selectedId);

            int affectedRows = deleteEmployee.executeUpdate();
            if(affectedRows != 1) {
                System.out.println("SQL DELETE failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean doesAlreadyExists(String username, String firstName, String lastName) {
        try {

            queryUsersIfExists.setString(1, username);
            queryUsersIfExists.setString(2, firstName);
            queryUsersIfExists.setString(3, lastName);
            ResultSet results = queryUsersIfExists.executeQuery();
            if(results.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public int getEmployeeIdByUsername(String username) {

        try {
            findEmployeeId.setString(1, username);

            ResultSet resultSet = findEmployeeId.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<TodoItem> getTodoItemsForUser(int id) {

        try {
            findTodoItems.setInt(1, id);
            ResultSet results = findTodoItems.executeQuery();

            List<TodoItem> todoList = new ArrayList<>();

            while(results.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(results.getInt(1));
                todoItem.setUserId(results.getInt(2));
                todoItem.setTitle(results.getString(3));
                todoItem.setDescription(results.getString(4));
                todoList.add(todoItem);
            }

            return todoList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addTaskForEmployee(int userId, String title, String description) {

        try {
            insertTodoItem.setInt(1, userId);
            insertTodoItem.setString(2, title);
            insertTodoItem.setString(3, description);

            int affectedRows = insertTodoItem.executeUpdate();
            if (affectedRows != 1) {
                System.out.println("Error adding a task");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
