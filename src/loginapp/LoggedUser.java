package loginapp;

public class LoggedUser {

    private int userId;
    private boolean isAdmin;
    private static LoggedUser instance = new LoggedUser();

    private LoggedUser() {}

    public static LoggedUser getInstance() {
        return instance;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
