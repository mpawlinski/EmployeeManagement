package loginapp;

public enum Options {
    Employee, Admin;

    private Options() {}

    public String getValue() {
        return name();
    }

    public static Options fromValue(String value) {
        return valueOf(value);
    }

}
