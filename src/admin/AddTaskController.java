package admin;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DataSource;
import model.Employee;

public class AddTaskController {

    @FXML
    private TextField userId;

    @FXML
    private TextField todoTitle;

    @FXML
    private TextField todoDescription;

    public void getSelectedEmployeeId(Employee employee) {

        userId.setText(Integer.toString(employee.getId()));
    }

    public void addTask(Employee employee) {

        int employeeId = employee.getId();
        String title = todoTitle.getText();
        String description = todoDescription.getText();

        DataSource.getInstance().addTaskForEmployee(employeeId, title, description);
    }
}
