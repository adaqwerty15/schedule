package ru.isu.tashkenova.appSch;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CreateScheduleView {

    private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty day;



    public CreateScheduleView(String name, Integer id, String day) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.day = new SimpleStringProperty(day);

    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    @Override
    public String toString() {
        return "CreateScheduleView{" +
                "name=" + name +
                ", id=" + id +
                ", day=" + day +
                '}';
    }
}
