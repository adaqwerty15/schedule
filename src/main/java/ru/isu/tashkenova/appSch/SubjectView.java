package ru.isu.tashkenova.appSch;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectView {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty groupNumber;
    private final SimpleStringProperty name;
    private final SimpleStringProperty shortName;


    public SubjectView(int id, int groupNumber,String name,String shortName) {
        this.id = new SimpleIntegerProperty(id);
        this.groupNumber = new SimpleIntegerProperty(groupNumber);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);

         }

    public int getId() {
        return id.get();
    }



    public SimpleIntegerProperty idProperty() {
        return id;
    }


    public int getGroupNumber() {
        return groupNumber.get();
    }


    public SimpleIntegerProperty groupNumberProperty() {
        return groupNumber;
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
