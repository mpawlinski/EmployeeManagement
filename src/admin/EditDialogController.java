package admin;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.DataSource;
import model.Employee;

public class EditDialogController {

    @FXML
    private TextField idField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<Employee> employeeTableView;

//    public void editEmployee(Employee employee) {
//        idField.setText(employee.getId());
//        firstNameField.setText(employee.getFirstName());
//        lastNameField.setText(employee.getLastName());
//        emailField.setText(employee.getEmail());
//    }
//
//    public void updateEmployee(Employee employee) {
//        String updatedId = idField.getText();
//        String updatedFirstName = firstNameField.getText();
//        String updatedLastName = lastNameField.getText();
//        String updatedEmail = emailField.getText();
//
//        String whereId = employee.getId();
//        String whereFirstName = employee.getFirstName();
//        String whereLastName = employee.getLastName();
//
//        DataSource.getInstance().updateEmployee(updatedId, updatedFirstName, updatedLastName, updatedEmail,
//                                                whereId, whereFirstName, whereLastName);
//    }
}
