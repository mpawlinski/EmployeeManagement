package employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import loginapp.LoggedUser;
import model.DataSource;
import model.TodoItem;

import java.util.List;

public class EmployeeController {

    @FXML
    private TableView<TodoItem> todoItemsTableView;

    @FXML
    private Label loggedUser;

    public void initialize() {

        loggedUser.setText(Integer.toString(LoggedUser.getInstance().getUserId()));
    }

    public void listTodoItems() {

        Task<ObservableList<TodoItem>> task = new GetTodoItems();
        todoItemsTableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }


//    private List<TodoItem> getItems() {
//        Long userId = LoggedUser.getInstance().getUsername();
//
//        List<TodoItem> items = DataSource.getInstance().getToDoItemsForUser(userId);
//    }


}

class GetTodoItems extends Task {

    int userId = LoggedUser.getInstance().getUserId();

    @Override
    public List<TodoItem> call() {

        return FXCollections.observableArrayList(DataSource.getInstance().getTodoItemsForUser(userId));
    }
}
