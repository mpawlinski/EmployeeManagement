package admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DataSource;
import model.Employee;

import java.sql.*;

public class AdminController {

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
    private TableView<Employee> employeeTableView;

    public void listEmployees() {
        Task<ObservableList<Employee>> task = new GetAllEmployees();
        employeeTableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    public void clearFields() {
        idTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        dobDatePickerField.setValue(null);
    }

    @FXML
    public void clearButton() {
        clearFields();
    }
}

class GetAllEmployees extends Task{

    @Override
    public ObservableList<Employee> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryEmployees());
    }
}

