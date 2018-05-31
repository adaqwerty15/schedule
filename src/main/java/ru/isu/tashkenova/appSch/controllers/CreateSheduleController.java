package ru.isu.tashkenova.appSch.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateSheduleController {
    @FXML
    private ComboBox<String> day;

    @FXML
    private Button createScheme;

    @FXML
    private TableView<?> scheme;

    @FXML
    private Button changeScheme;

    @FXML
    private Button deleteScheme;
    ArrayList<String> a;


    public void initialize () {
        a= new ArrayList<String>(Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"));
        day.setItems(FXCollections.observableArrayList(a));
        day.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                day.setStyle("-fx-border-color:#fff");
            }
        });
    }

    @FXML
    void changeSchemeClick(ActionEvent event) {

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



    @FXML
    void dayClick(ActionEvent event) {

    }

    @FXML
    void deleteSchemeClick(ActionEvent event) {

    }
}
