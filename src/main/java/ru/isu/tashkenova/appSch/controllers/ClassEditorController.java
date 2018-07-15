package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
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

    @FXML
    private Label name;

    @FXML
    private Label parallel;

    @FXML
    private Label shift;

    @FXML
    private Button save;

    UserService service;
    ObservableList<Subject> contentSubject;
    ObservableList<User> contentTeachers;
    public  static  ObservableList<WorkloadViewNew> data = FXCollections.observableArrayList();
    String code;
    int class_id;
            
    HashMap<Integer, User> teachers_map;
    HashMap<Integer, Subject> subject_map;

    public void setClassCode(String code) throws IOException {
        this.code = code;
        nameClass.setText(code);

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        service = RetrofitService.RetrofitBuildU();

        Response<List<Subject>> subjects = service.getSubject().execute();
        Response<List<User>> teachers = service.getUsers().execute();

        Response<StudentsClass> stud = service.getStudetsClassWithCode(code).execute();
        class_id = stud.body().getId();
        name.setText(code);
        parallel.setText(String.valueOf(stud.body().getCourse()));
        shift.setText(String.valueOf(stud.body().getShiftId()));

        Response<List<Workload>> workload = service.getWorkloadWithId(class_id).execute();

        contentTeachers = FXCollections.observableArrayList(
                teachers.body()
        );

        contentSubject = FXCollections.observableArrayList(
                subjects.body()
        );

        teachers_map = new HashMap<>();
        for (User u: contentTeachers) {
            if (u.getRoleId() <2)
            teachers_map.put(u.getId(),u);
        }

        subject_map = new HashMap<>();
        for (Subject s:contentSubject) {
            subject_map.put(s.getId(),s);
        }

        columnName.setCellValueFactory(celldata -> celldata.getValue().teacherNameProperty());
        columnNameSubject.setCellValueFactory(celldata -> celldata.getValue().subjectNameProperty());



//        workload.add(new Workload(1, 15, 6));
//        workload.add(new Workload(1, 16, 14));
//        workload.add(new Workload(1, 17, 22));
//        workload.add(new Workload(1, 18, 4));

//        workload.add(new Workload(2, 1, 9));
//        workload.add(new Workload(2, 2, 5));
//        workload.add(new Workload(2, 3, 21));
//        workload.add(new Workload(2, 4, 7));
//        workload.add(new Workload(2, 5, 17));
//
//        workload.add(new Workload(2, 7, 25));
//        workload.add(new Workload(2, 8, 6));
//        workload.add(new Workload(2, 9, 15));
//        workload.add(new Workload(2, 10, 12));
//        workload.add(new Workload(2, 11, 25));

        data.clear();
        for (Workload w: workload.body()) {
            Workload workload1 = gson.fromJson(gson.toJson(w), Workload.class);
            data.add(new WorkloadViewNew(teachers_map.get(workload1.getUserId()).
                    getSurname() + " "+ teachers_map.get(workload1.getUserId()).getName().substring(0,1) + "."+
                    teachers_map.get(workload1.getUserId()).getFathername().substring(0,1)+".",
                    subject_map.get(workload1.getSubjectId()).getName(), workload1.getId()));
        }

        tableClasses.setItems(data);
    }

    public String getClassCode() {
        return this.code;

    }

    public void initialize() throws IOException {
    }

    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/addWorkload.fxml"));
        Parent root_add = fxmlLoader.load();
        AddWorkloadController c = fxmlLoader.getController();
        c.setData(contentSubject,contentTeachers, class_id);
        stage.setTitle("Добавить нагрузку");
        stage.setScene(new Scene(root_add, 400, 260));
        stage.show();
        //tableClasses.refresh();
    }  
   
    public void changeButtonClicked(ActionEvent actionEvent) throws IOException {
        WorkloadViewNew workloadViewNew = tableClasses.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/changeWorkload.fxml"));
        Parent root_add = fxmlLoader.load();
        ChangeWorkloadController c = fxmlLoader.getController();
        c.setData(contentSubject,contentTeachers, class_id, workloadViewNew, tableClasses.getSelectionModel().getSelectedIndex());
        stage.setTitle("Изменить нагрузку");
        stage.setScene(new Scene(root_add, 526, 260));
        stage.show();
    }

    public void deleteSubButtonClicked(ActionEvent actionEvent) throws IOException {
        WorkloadViewNew workloadViewNew = tableClasses.getSelectionModel().getSelectedItem();
        service.deleteWorkload(workloadViewNew.getId()).execute();
        data.remove(tableClasses.getSelectionModel().getSelectedIndex());
    }
 
    public void saveWorkloadButtonClicked(ActionEvent actionEvent) {
        Stage stag = (Stage)save.getScene().getWindow();
        stag.close();
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
    }
}
