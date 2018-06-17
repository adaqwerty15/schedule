package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassEditorController {
    @FXML
    private TableView<WorkloadViewNew> tableClasses= new TableView<>();

    @FXML
    private TableColumn<WorkloadViewNew, String> columnName;

    @FXML
    private TableColumn<WorkloadViewNew, String> columnNameSubject;

    @FXML
    private Label nameClass;

    UserService service;
    ObservableList<Subject> contentSubject;
    ObservableList<User> contentTeachers;
    ObservableList<Role> contentRole;
    ObservableList<WorkloadViewNew> contentWorkloadViewNew = FXCollections.observableArrayList();
    String code;

    public void setClassCode(String code) throws IOException {
        this.code = code;

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();

        service = RetrofitService.RetrofitBuildU();

        Response<List<Subject>> subjects = service.getSubject().execute();


        Response<List<User>> teachers = service.getUsers().execute();

        contentTeachers = FXCollections.observableArrayList(
                teachers.body()
        );



        HashMap<Integer, User> teachers_map = new HashMap<>();
        for (int i=0; i<contentTeachers.size(); i++) {
            teachers_map.put(contentTeachers.get(i).getId(),contentTeachers.get(i));
        }

        for (HashMap.Entry<Integer, User> entry : teachers_map.entrySet()) {
            System.out.println(entry.getValue().getName());
        }


        contentSubject = FXCollections.observableArrayList(
                subjects.body()
        );

        nameClass.setText(code);

        HashMap<Integer, Subject> subject_map = new HashMap<>();
        for (int i=0; i<contentSubject.size(); i++) {
            subject_map.put(contentSubject.get(i).getId(),contentSubject.get(i));
        }

        for (HashMap.Entry<Integer, Subject> entry : subject_map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().id + " " + entry.getValue().name);
        }


        columnName.setCellValueFactory(celldata -> celldata.getValue().teacherNameProperty());
        columnNameSubject.setCellValueFactory(celldata -> celldata.getValue().subjectNameProperty());

        //Response<List<Workload>> workload = service.getWorkload().execute();

        ArrayList<Workload> workload = new ArrayList<>();
        workload.add(new Workload(1, 1, 9));
        workload.add(new Workload(1, 2, 5));
        workload.add(new Workload(1, 3, 21));
        workload.add(new Workload(1, 4, 7));
        workload.add(new Workload(1, 5, 17));
        workload.add(new Workload(1, 5, 26));
        workload.add(new Workload(1, 6, 8));
        workload.add(new Workload(1, 6, 14));
        workload.add(new Workload(1, 7, 25));
        workload.add(new Workload(1, 8, 6));
        workload.add(new Workload(1, 9, 15));
        workload.add(new Workload(1, 10, 12));
        workload.add(new Workload(1, 11, 25));
        workload.add(new Workload(1, 12, 5));
        workload.add(new Workload(1, 13, 5));
        workload.add(new Workload(1, 14, 15));
        workload.add(new Workload(1, 15, 6));
        workload.add(new Workload(1, 16, 8));
        workload.add(new Workload(1, 16, 14));
        workload.add(new Workload(1, 17, 22));
        workload.add(new Workload(1, 18, 4));

        workload.add(new Workload(2, 1, 9));
        workload.add(new Workload(2, 2, 5));
        workload.add(new Workload(2, 3, 21));
        workload.add(new Workload(2, 4, 7));
        workload.add(new Workload(2, 5, 17));
        workload.add(new Workload(2, 5, 26));
        workload.add(new Workload(2, 7, 25));
        workload.add(new Workload(2, 8, 6));
        workload.add(new Workload(2, 9, 15));
        workload.add(new Workload(2, 10, 12));
        workload.add(new Workload(2, 11, 25));
        workload.add(new Workload(2, 12, 5));
        workload.add(new Workload(2, 13, 5));
        workload.add(new Workload(2, 14, 15));
        workload.add(new Workload(2, 15, 6));
        workload.add(new Workload(2, 16, 8));
        workload.add(new Workload(2, 16, 14));
        workload.add(new Workload(2, 17, 22));
        workload.add(new Workload(2, 18, 4));
        workload.add(new Workload(2, 19, 16));

        for (int i=0; i< workload.size(); i++) {
            Workload workload1 = gson.fromJson(gson.toJson(workload.get(i)), Workload.class);
            contentWorkloadViewNew.add(new WorkloadViewNew(teachers_map.get(workload1.getUserId()).getSurname(),
                    subject_map.get(workload1.getSubjectId()).getName()));
        }

        tableClasses.setItems(contentWorkloadViewNew);



//        System.out.println(classes.body().toString());
//        System.out.println(code);
    }

    public String getClassCode() {
        return this.code;

    }

    public void initialize() throws IOException {



    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
    }

    public void tableClicked(MouseEvent mouseEvent) {
    }

    public void saveWorkloadButtonClicked(ActionEvent actionEvent) {
    }

    public void deleteSubButtonClicked(ActionEvent actionEvent) {
    }

    public void changeButtonClicked(ActionEvent actionEvent) {
    }
}
