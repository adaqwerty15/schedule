package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateSheduleController {
    @FXML
    private ComboBox<String> day;

    @FXML
    private Button createScheme;

    @FXML
    private TableView<CreateScheduleView> scheme = new TableView<>();

    @FXML
    private TableColumn<CreateScheduleView, String> columnName;

    @FXML
    private TableColumn<CreateScheduleView, String> columnDay;

    @FXML
    private Button changeScheme;

    @FXML
    private Button deleteScheme;
    ArrayList<String> a;
    WorkloadService service;
    public  static ObservableList<CreateScheduleView> data = FXCollections.observableArrayList();


    public void initialize () throws IOException {
        a= new ArrayList<String>(Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"));
        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        service = RetrofitService.RetrofitBuildW();

        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
        columnDay.setCellValueFactory(celldata -> celldata.getValue().dayProperty());

        Response<List<ScheduleOwner>> schemes = service.getscheduleOwner().execute();


        for (ScheduleOwner w: schemes.body()) {
            ScheduleOwner owner = gson.fromJson(gson.toJson(w), ScheduleOwner.class);
            data.add(new CreateScheduleView(owner.getName(), owner.getId(), a.get(owner.getDayId()), owner.numberOfTheSchemaId));
            //a.get(owner.getDayId())
        }

        scheme.setItems(data);



        day.setItems(FXCollections.observableArrayList(a));
        day.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                day.setStyle("-fx-border-color:#fff");
            }
        });

        scheme.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                CreateScheduleView scheduleView = scheme.getSelectionModel().getSelectedItem();
            }
        });
    }

    @FXML
    void changeSchemeClick(ActionEvent event) throws IOException {
        CreateScheduleView scheduleView = scheme.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/rasp_change.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Change");
        SchemeChangeController c = fxmlLoader.getController();
        c.setView(scheduleView);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width;
        double height = screenSize.getHeight();
        stage.setScene(new Scene(root, width-1, height-80));
        stage.show();
    }

    @FXML
    void createSchemeClick(ActionEvent event) throws IOException {

        int d = a.indexOf(day.getValue());

        if (d>=0) {
            Stage stage = new Stage();
            day.setStyle("-fx-border-color:#fff");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/rasp.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Create");
            SchemeController c = fxmlLoader.getController();
            c.setDayId(d);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.width;
            double height = screenSize.getHeight();
            stage.setScene(new Scene(root, width-1, height-80));
            stage.show();
        }
        else day.setStyle("-fx-border-color:#ff0000");

    }


    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }

    @FXML
    void dayClick(ActionEvent event) {

    }

    @FXML
    void deleteSchemeClick(ActionEvent event) throws IOException {
        CreateScheduleView scheduleView = scheme.getSelectionModel().getSelectedItem();
        service.deletescheduleOwner(scheduleView.getId()).execute();
        service.deleteSchedule(scheduleView.getNumberoftheSchema()).execute();
        data.remove(scheme.getSelectionModel().getSelectedIndex());


    }
}
