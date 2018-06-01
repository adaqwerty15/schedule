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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.Cabinet;
import ru.isu.tashkenova.appSch.CabinetView;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.UserService;

import java.io.IOException;
import java.util.List;

public class ClassroomController {

    @FXML
    private Button save;

    public static ObservableList<CabinetView> data = FXCollections.observableArrayList();

    UserService service;
    ObservableList content;

    @FXML
    private TableView<CabinetView> tableClassroom = new TableView<>();

    @FXML
    private TableColumn<CabinetView,Integer>  columnId;

    @FXML
    private TableColumn<CabinetView,String> columnName;

    @FXML
    void initialize() throws IOException {
        columnId.setCellValueFactory(celldata -> celldata.getValue().idProperty().asObject());
        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());

        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        service = RetrofitService.RetrofitBuild();

        Response<List<Cabinet>> cabinets = service.getCabinet().execute();
        content = FXCollections.observableArrayList(
                cabinets.body()
        );
        for (Cabinet w: cabinets.body()) {
            Cabinet cabinet = gson.fromJson(gson.toJson(w), Cabinet.class);
            data.add(new CabinetView(cabinet.getId(),cabinet.getName()));
        }
        tableClassroom.setItems(data);
    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/addClass.fxml"));
        Parent root_add = fxmlLoader.load();
        AddClassController c = fxmlLoader.getController();
        stage.setTitle("Добавить класс");
        stage.setScene(new Scene(root_add, 456, 439));
        stage.show();
        //usersTable.refresh();
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) throws IOException {
        CabinetView cabinet = tableClassroom.getSelectionModel().getSelectedItem();
        service.deleteCabinet(cabinet.getId()).execute();
        data.remove(tableClassroom.getSelectionModel().getSelectedIndex());
        //tableClassroom.refresh();
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        Stage stag = (Stage) save.getScene().getWindow();
        stag.close();
    }

    @FXML
    void tableClicked(MouseEvent event) {

    }
}
