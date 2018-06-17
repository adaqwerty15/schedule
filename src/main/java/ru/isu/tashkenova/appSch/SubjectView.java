package ru.isu.tashkenova.appSch;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectView {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty groupName;
    private final SimpleStringProperty name;
    private final SimpleStringProperty shortName;


    public SubjectView(int id, int groupName,String name,String shortName) {
        this.id = new SimpleIntegerProperty(id);
        this.groupName = new SimpleIntegerProperty(groupName);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);

         }

    public int getId() {
        return id.get();
    }



    public SimpleIntegerProperty idProperty() {
        return id;
    }


    public int getGrouoName() {
        return groupName.get();
    }


    public SimpleIntegerProperty groupNameProperty() {
        return groupName;
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getShortName() {
        return shortName.get();
    }

    public SimpleStringProperty shortNameProperty() {
        return shortName;
    }
}
