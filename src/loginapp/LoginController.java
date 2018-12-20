package loginapp;

import admin.AdminController;
import employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Options> comboBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginStatus;


    public void initialize() {
        // status label
        if(loginModel.isDatabaseConnected()) {
            dbstatus.setText("Connected");
            dbstatus.setTextFill(Color.MEDIUMSEAGREEN);
        } else {
            dbstatus.setText("Not connected");
            dbstatus.setTextFill(Color.DARKRED);
        }

        // comboBox
        comboBox.setItems(FXCollections.observableArrayList(Options.values()));
    }

    @FXML
    public void login(ActionEvent event) {
        // when login button's pressed - close login window
        if(loginModel.isLoggedIn(username.getText(), password.getText(), comboBox.getValue().toString())) {

            // closing login window after successfully logging in
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();

            switch(comboBox.getValue().toString()) {
                case "Employee":
                    employeeLogin();
                    break;
                case "Admin":
                    adminLogin();
                    break;
            }

        } else {
            loginStatus.setText("Incorrect");
            loginStatus.setTextFill(Color.RED);
        }
    }

    public void employeeLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Stage employeeStage = new Stage();
            Pane root = (Pane)loader.load(getClass().getResource("/employee/employee.fxml").openStream());
            EmployeeController employeeController = loader.getController();
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
