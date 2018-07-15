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
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
import java.util.List;

public class ClassroomController {

    @FXML
    private Button save;

    @FXML
    private Button change;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    public static ObservableList<CabinetView> data = FXCollections.observableArrayList();
    UserService service;

    @FXML
    private TableView<CabinetView> tableClassroom = new TableView<>();;

    @FXML
    private TableColumn<CabinetView,Integer>  columnId;

    @FXML
    private TableColumn<CabinetView,String> columnName;

    ObservableList content;

    @FXML
    void initialize() throws IOException {
        columnId.setCellValueFactory(celldata -> celldata.getValue().idProperty().asObject());
        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());

        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        service = RetrofitService.RetrofitBuildU();

        Response<List<Cabinet>> users = service.getCabinet().execute();

        data.clear();
        for (Cabinet w: users.body()) {
            Cabinet cabinet = gson.fromJson(gson.toJson(w), Cabinet.class);
            data.add(new CabinetView(cabinet.getId(),cabinet.getName()));
        }
        tableClassroom.setItems(data);
    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/addCabinet.fxml"));
        Parent root_add = fxmlLoader.load();
        AddClassroomController c = fxmlLoader.getController();
        stage.setTitle("Добавить кабинет");
        stage.setScene(new Scene(root_add, 456, 200));
        stage.show();
        //tableClassroom.refresh();
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) throws IOException {
        CabinetView cab = tableClassroom.getSelectionModel().getSelectedItem();
        service.deleteCabinet(cab.getId()).execute();
        data.remove(tableClassroom.getSelectionModel().getSelectedIndex());
       // tableClassroom.refresh();

    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        Stage stag = (Stage) save.getScene().getWindow();
        stag.close();
    }

    @FXML
    public void changeButtonClicked(ActionEvent actionEvent) throws IOException {
        CabinetView cab = tableClassroom.getSelectionModel().getSelectedItem();
        Stage stage_add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/changeCabinet.fxml"));
        Parent root_add = fxmlLoader.load();
        ChangeCabinetController c = fxmlLoader.getController();
        c.setCabinet(cab, tableClassroom.getSelectionModel().getSelectedIndex());
        stage_add.setTitle("Изменить кабинет");
        stage_add.setScene(new Scene(root_add, 456, 200));
        stage_add.show();

        //tableClassroom.refresh();
    }


    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }

}
