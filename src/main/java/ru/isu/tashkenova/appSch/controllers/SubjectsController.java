package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.Subject;
import ru.isu.tashkenova.appSch.SubjectView;
import ru.isu.tashkenova.appSch.UserService;

import java.io.IOException;
import java.util.List;

public class SubjectsController  implements ListEditor{
    @FXML
    private Button save;


    public static ObservableList<SubjectView> data = FXCollections.observableArrayList();

    UserService service;
    ObservableList content;


    @FXML
    private TableView<SubjectView> tableSubject = new TableView<>();

    @FXML
    private TableColumn<SubjectView,Integer> columnId;

    @FXML
    private TableColumn<SubjectView,String> columnName;

    @FXML
    private TableColumn<SubjectView,String> columnShortName;

    @FXML
    private TableColumn<SubjectView,Integer> columnGroup;




    @Override
    public void initialize() throws IOException {

        columnId.setCellValueFactory(celldata -> celldata.getValue().idProperty().asObject());
        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
        columnShortName.setCellValueFactory(celldata -> celldata.getValue().shortNameProperty());
        columnGroup.setCellValueFactory(celldata -> celldata.getValue().groupNumberProperty().asObject());


        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();
        service = RetrofitService.RetrofitBuildU();



        Response<List<Subject>> subjects = service.getSubject().execute();
        content = FXCollections.observableArrayList(
                subjects.body()

        );

        for (Subject w: subjects.body()) {
            Subject subject = gson.fromJson(gson.toJson(w), Subject.class);
            data.add(new SubjectView(subject.getId(),subject.getGroupNumber(), subject.getName(), subject.getShortName()));

        }
        tableSubject.setItems(data);


    }


    @Override
    public void saveButtonClicked(ActionEvent event) {

        Stage stag = (Stage)save.getScene().getWindow();
        stag.close();
    }


    @Override
    public void deleteButtonClicked(ActionEvent actionEvent) throws IOException {

        SubjectView subject = tableSubject.getSelectionModel().getSelectedItem();
        service.deleteSubject(subject.getId()).execute();
        data.remove(tableSubject.getSelectionModel().getSelectedIndex());
        tableSubject.refresh();

    }

    @Override
    public void addButtonClicked(ActionEvent actionEvent) throws IOException, Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/addSubject.fxml"));
        Parent root_add = fxmlLoader.load();
        AddSubjectController c = fxmlLoader.getController();
        stage.setTitle("Добавить предмет");
        stage.setScene(new Scene(root_add, 449, 423));
        stage.show();
        tableSubject.refresh();
    }


    public void changeButtonClicked(ActionEvent actionEvent) throws IOException {
        SubjectView subject = tableSubject.getSelectionModel().getSelectedItem();
        Stage stage_add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/change_subject.fxml"));
        Parent root_add = fxmlLoader.load();
        ChangeSubjectController c = fxmlLoader.getController();
        c.setForm(subject, tableSubject.getSelectionModel().getSelectedIndex());
        stage_add.setTitle("Изменить предмет");
        stage_add.setScene(new Scene(root_add, 449, 423));
        stage_add.show();

        //usersTable.refresh();
    }

    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }
}
