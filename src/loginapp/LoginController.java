package loginapp;

import admin.AdminController;
import employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DataSource;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label dbstatus;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ComboBox<Options> comboBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginStatus;


    public void initialize() {

        if(DataSource.getInstance().isDatabaseConnected()) {
            dbstatus.setText("Connected");
            dbstatus.setTextFill(Color.MEDIUMSEAGREEN);
        } else {
            dbstatus.setText("Not connected");
            dbstatus.setTextFill(Color.DARKRED);
        }

        comboBox.setItems(FXCollections.observableArrayList(Options.values())); // setting comboBox values
        comboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void login() {

        if(DataSource.getInstance().isLoggedIn(usernameTextField.getText(), passwordTextField.getText(), comboBox.getValue().toString())) {

            Stage stage = (Stage) loginButton.getScene().getWindow(); // closing login window after
            stage.close();                                            // successfully logging in

            switch(comboBox.getValue().toString()) {
                case "employee":
                    int userId = getUserIdFromUsername(usernameTextField.getText());
//                    System.out.println(userId); // test
                    employeeLogin(userId);
                    break;
                case "admin":
                    adminLogin();
                    break;
            }

        } else {
            loginStatus.setText("Incorrect");
            loginStatus.setTextFill(Color.RED);
        }
    }

    private int getUserIdFromUsername(String username) {
        return DataSource.getInstance().getEmployeeIdByUsername(username);
    }

    public void employeeLogin(int userId) {

        LoggedUser.getInstance().setUserId(userId);

        try {
            FXMLLoader loader = new FXMLLoader();
            Stage employeeStage = new Stage();
            Pane root = loader.load(getClass().getResource("/employee/employee.fxml").openStream());
            EmployeeController employeeController = loader.getController();
            employeeController.listTodoItems();
            Scene scene = new Scene(root);

            employeeStage.setScene(scene);
            employeeStage.setTitle("Employee");
            employeeStage.resizableProperty().setValue(false);
            employeeStage.show();

        } catch(IOException e) {
            System.out.println("Error while loading. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adminLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/admin.fxml"));
            Stage adminStage = new Stage();
            Parent root = loader.load();
            AdminController adminController = loader.getController();
            adminController.listEmployees();
            Scene adminScene = new Scene(root);

            adminStage.setScene(adminScene);
            adminStage.setTitle("Admin");
            adminStage.resizableProperty().setValue(false);
            adminStage.show();

        } catch(IOException e) {
            System.out.println("Error while loading. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
