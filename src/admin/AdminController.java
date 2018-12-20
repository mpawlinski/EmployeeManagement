package admin;

import DatabaseUtility.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    public static final String QUERY_EMPLOYEES = "SELECT * FROM employees";
    public static final String INSERT_EMPLOYEE = "INSERT INTO employees(id, firstname, lastname, email, dateofbirth)" +
            " VALUES (?, ?, ?, ?, ?)";

    @FXML
    private TextField idTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private DatePicker dobDatePickerField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loadButton;

    @FXML
    private TableView<EmployeeData> employeeTableView;

    @FXML
    private TableColumn<EmployeeData, String> idColumn;

    @FXML
    private TableColumn<EmployeeData, String> firstNameColumn;

    @FXML
    private TableColumn<EmployeeData, String> lastNameColumn;

    @FXML
    private TableColumn<EmployeeData, String> emailColumn;

    @FXML
    private TableColumn<EmployeeData, String> dobColumn;

    private Connection connection;

    private ObservableList<EmployeeData> employeeList;

    public void initialize() {
        connection = DatabaseConnection.getConnection();

    }

    @FXML
    public void loadEmployeeList() {

        employeeList = FXCollections.observableArrayList();

        try {
            ResultSet results = connection.createStatement().executeQuery(QUERY_EMPLOYEES);
            while(results.next()) {
                EmployeeData employee = new EmployeeData();

                employee.setId(results.getString(1));
                employee.setFirstName(results.getString(2));
                employee.setLastName(results.getString(3));
                employee.setEmail(results.getString(4));
                employee.setDateOfBirth(results.getString(5));
                employeeList.add(employee);
            }

        } catch(SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("email"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("dateOfBirth"));

        employeeTableView.setItems(employeeList);
    }

    @FXML
    public void addEmployee() {

        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement insertEmployeeStatement = connection.prepareStatement(INSERT_EMPLOYEE);

            insertEmployeeStatement.setString(1, idTextField.getText());
            insertEmployeeStatement.setString(2, firstNameTextField.getText());
            insertEmployeeStatement.setString(3, lastNameTextField.getText());
            insertEmployeeStatement.setString(4, emailTextField.getText());
            insertEmployeeStatement.setString(5, dobDatePickerField.getEditor().getText());

            insertEmployeeStatement.execute();

        } catch(SQLException e) {
            e.getMessage();
        }
    }


    // implement

    // clear method after save was called
    // clear button functionality
    // change structure: singleton to access connections, put there all statements and access them via singleton
    // open connection in init() in main method
    // check if connection, prepared statements !- null and close them -> checking in singleton class (data source)
}
