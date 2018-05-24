package ru.isu.tashkenova.appSch;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserView {

    private final SimpleStringProperty name;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty fathername;
    private final SimpleStringProperty login;
    private final SimpleIntegerProperty roleId;
    private final SimpleIntegerProperty id;

    public UserView(String name, String lastname, String fathername, String login, Integer roleId, Integer id) {
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.fathername = new SimpleStringProperty(fathername);
        this.login = new SimpleStringProperty(login);
        this.roleId = new SimpleIntegerProperty(roleId);
        this.id = new SimpleIntegerProperty(id);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
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

    @Override
    public String toString() {
        return "UserView{" +
                "name=" + name +
                ", lastname=" + lastname +
                ", fathername=" + fathername +
                ", login=" + login +
                ", roleId=" + roleId +
                ", id=" + id +
                '}';
    }
}
