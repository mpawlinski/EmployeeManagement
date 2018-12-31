package admin;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DataSource;
import model.Employee;

public class EditDialogController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    public void editEmployee(Employee employee) {

        usernameField.setText(employee.getUsername());
        passwordField.setText(employee.getPassword());
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        emailField.setText(employee.getEmail());
        phoneNumberField.setText(Integer.toString(employee.getPhoneNumber()));
    }

    public void updateEmployee(Employee employee) {

        String updatedUsername = usernameField.getText();
        String updatedPassword = passwordField.getText();
        String updatedFirstName = firstNameField.getText();
        String updatedLastName = lastNameField.getText();
        String updatedEmail = emailField.getText();
        int updatedPhoneNumber = Integer.parseInt(phoneNumberField.getText());

        int whereUsernameId = employee.getId();

        DataSource.getInstance().updateEmployee(updatedUsername, updatedPassword, updatedFirstName, updatedLastName,
                                                updatedEmail, updatedPhoneNumber, whereUsernameId);
    }
}
