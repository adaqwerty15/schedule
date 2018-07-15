package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
import java.util.ArrayList;

public class ChangeWorkloadController {
    @FXML
    private ChoiceBox subject;

    @FXML
    private ChoiceBox teacher;

    int classId;
    ObservableList<Subject> contentSubject;
    ObservableList<User> contentTeachers;
    int stId;
    int id;

    public void setData(ObservableList<Subject> contentSubject, ObservableList<User> contentTeachers, int classId,
                        WorkloadViewNew workload, int stId) {
        this.id = workload.getId();
        this.stId = stId;
        this.contentSubject = contentSubject;
        this.contentTeachers = contentTeachers;

        ArrayList<String> teachers= new ArrayList<>();
        for ( User t: contentTeachers) {
            if (t.getRoleId() <2) {
                teachers.add(t.getSurname()+" "+t.getName().substring(0,1)+"."+t.getFathername().substring(0,1)+".");
                //contentTeachers.remove(t);
            }

        }

        ArrayList<String> subjects= new ArrayList<>();
        for ( Subject s: contentSubject) {
            subjects.add(s.getName());
        }

        this.classId = classId;
        subject.setItems( FXCollections.observableArrayList(subjects));
        teacher.setItems( FXCollections.observableArrayList(teachers));

        subject.setValue(workload.getSubjectName());
        teacher.setValue(workload.getTeacherFIO());
        System.out.println(workload.getTeacherFIO());


    }


    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        int tea = contentTeachers.get(teacher.getSelectionModel().getSelectedIndex()).getId();
        int subj = contentSubject.get(subject.getSelectionModel().getSelectedIndex()).getId();

        Workload workload = new Workload(0, classId, subj, tea, 0);
        RetrofitService.RetrofitBuildU().putWorkload(id,workload).execute();
        ClassEditorController.data.set(stId, new WorkloadViewNew(String.valueOf(teacher.getValue()),String.valueOf(subject.getValue()), workload.getId() ));

        subject.getScene().getWindow().hide();

    }
}
