package loginapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Options> comboBox;

    @FXML
    private Button lognButton;

    public void initialize() {}

}
