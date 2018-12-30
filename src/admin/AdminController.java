package admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.DataSource;
import model.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class AdminController {

    @FXML
    private AnchorPane mainPanel;

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

//    public void listEmployees() {
//        Task<ObservableList<Employee>> task = new GetAllEmployees();
//        employeeTableView.itemsProperty().bind(task.valueProperty());
//
//        new Thread(task).start();
//    }

//    @FXML
//    public void insertEmployee() throws SQLException {
//        String id = idTextField.getText();
//        String firstName = firstNameTextField.getText();
//        String lastName = lastNameTextField.getText();
//        String email = emailTextField.getText();
//        String dob = dobDatePickerField.getEditor().getText();
//
//        if(validateInput()) {
//            if(!DataSource.getInstance().doesAlreadyExists(id, firstName, lastName, email)) {
//                DataSource.getInstance().addEmployee(id, firstName, lastName, email, dob);
//                listEmployees();
//                clear();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Cannot add employee");
//                alert.setHeaderText("Employee already exists");
//                alert.showAndWait();
//                clear();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Employee cannot be created");
//            alert.setHeaderText("Empty fields must be filled.");
//            alert.showAndWait();
//        }
//
//    }

    public void clearFields() {
        idTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        dobDatePickerField.setValue(null);
    }

    @FXML
    public void clear() {
        clearFields();
    }

//    @FXML
//    public void showEditDialog() {
//        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
//        if(selectedEmployee == null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("No employee selected");
//            alert.setHeaderText(null);
//            alert.setContentText("Select employee you want to edit.");
//            alert.showAndWait();
//            return;
//        }
//
//        Dialog<ButtonType> dialog = new Dialog<>();
//        dialog.initOwner(mainPanel.getScene().getWindow());
//        dialog.setTitle("Edit contact");
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("editdialog.fxml"));
//
//        try {
//            dialog.getDialogPane().setContent(fxmlLoader.load());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Couldn't load the dialog " + e.getMessage());
//            return;
//        }
//
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//
//        EditDialogController editDialogController = fxmlLoader.getController();
//        editDialogController.editEmployee(selectedEmployee);
//
//        Optional<ButtonType> result = dialog.showAndWait();
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//            editDialogController.updateEmployee(selectedEmployee);
//            listEmployees();
//        }
//    }

//    @FXML
//    public void deleteEmployee() {
//        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();
//        DataSource.getInstance().deleteEmployee(employee.getId(), employee.getFirstName(), employee.getLastName());
//        listEmployees();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Confirmation");
//        alert.setHeaderText("Employee Deleted");
//        alert.showAndWait();
//    }
//
//    public boolean validateInput() {
//        if(idTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()
//        || lastNameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || dobDatePickerField.getEditor().getText().isEmpty()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}

//class GetAllEmployees extends Task{
//
//    @Override
//    public ObservableList<Employee> call() {
//        return FXCollections.observableArrayList(DataSource.getInstance().queryEmployees());
//    }
//}

