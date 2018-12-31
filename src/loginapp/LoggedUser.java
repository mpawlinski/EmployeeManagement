package loginapp;

public class LoggedUser {

    private static LoggedUser instance = new LoggedUser();
    private int userId;
    private boolean isAdmin;

    private LoggedUser() {}

    public static LoggedUser getInstance() {
        return instance;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUsername(int userId) {
        this.userId = userId;
    }

    public int getUsername() {
        return userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
