package ru.isu.tashkenova.appSch;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WorkloadViewNew {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty teacherFIO;
    private final SimpleStringProperty subjectName;

    public WorkloadViewNew(String teacherFIO,  String subjectName, Integer id) {
        this.teacherFIO = new SimpleStringProperty(teacherFIO);
        this.subjectName = new SimpleStringProperty(subjectName);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getTeacherName() {
        return teacherFIO.get();
    }

    public SimpleStringProperty teacherNameProperty() {
        return teacherFIO;
    }

    public void setTeacherName(String teacherName) {
        this.teacherFIO.set(teacherName);
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public SimpleStringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getTeacherFIO() {
        return teacherFIO.get();
    }

    public SimpleStringProperty teacherFIOProperty() {
        return teacherFIO;
    }
}
