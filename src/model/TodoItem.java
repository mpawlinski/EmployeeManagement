package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TodoItem {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty userId;
    private SimpleStringProperty description;

    public TodoItem() {
        this.id = new SimpleIntegerProperty();
        this.userId = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
