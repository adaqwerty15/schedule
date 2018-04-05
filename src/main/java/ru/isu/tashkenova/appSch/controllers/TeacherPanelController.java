package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by adaqwerty15 on 28.03.2018.
 */
public class TeacherPanelController extends ViewController{

    @FXML
    private Label l;

    @FXML
    void ButtonExitClick(ActionEvent event)  throws Exception{
        l.getScene().getWindow().hide();
        Stage stage_in = new Stage();
        Parent root_in = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));
        stage_in.setTitle("Shedule");
        stage_in.setScene(new Scene(root_in, 288, 276));
        stage_in.show();

    }
}
