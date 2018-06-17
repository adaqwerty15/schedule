package ru.isu.tashkenova.appSch;

import javafx.beans.property.SimpleStringProperty;

public class WorkloadViewNew {
    private final SimpleStringProperty teacherName;
    private final SimpleStringProperty subjectName;

    public WorkloadViewNew(String teacherName, String subjectName) {
        this.teacherName = new SimpleStringProperty(teacherName);
        this.subjectName = new SimpleStringProperty(subjectName);
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public SimpleStringProperty teacherNameProperty() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
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
}
