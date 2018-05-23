package ru.isu.tashkenova.appSch;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class UserView {

    private final StringProperty name;
    private final StringProperty lastname;
    private final StringProperty fathername;
    private final StringProperty login;
    private final IntegerProperty roleId;

    public UserView(StringProperty name, StringProperty lastname, StringProperty fathername, StringProperty login, IntegerProperty roleId) {
        this.name = name;
        this.lastname = lastname;
        this.fathername = fathername;
        this.login = login;
        this.roleId = roleId;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getFathername() {
        return fathername.get();
    }

    public StringProperty fathernameProperty() {
        return fathername;
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public int getRoleId() {
        return roleId.get();
    }

    public IntegerProperty roleIdProperty() {
        return roleId;
    }
}
