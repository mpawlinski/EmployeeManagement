package loginapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    public void initialize() {
        // status label
        if(loginModel.isDatabaseConnected()) {
            dbstatus.setText("Connected");
            dbstatus.setTextFill(Color.GREEN);
        } else {
            dbstatus.setText("Not connected");
            dbstatus.setTextFill(Color.RED);
        }

        // comboBox
        comboBox.setItems(FXCollections.observableArrayList(Options.values()));
    }

}
