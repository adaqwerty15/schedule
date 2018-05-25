package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class MainController {
    @FXML
    private TextField login;

    @FXML
    private Label mistake;

    @FXML
    void enterButtonClicked(ActionEvent event)  throws Exception{
        Boolean l = false;
        String log = login.getText();
        String item = "";

        switch (log) {
            case "admin":
                 item = "views/admindashboard.fxml";
                 l = true;
                break;
            case "teacher":
                System.out.println("teacher");
                item = "views/teacherdashboard.fxml";
                l = true;
                break;
            case "zavuch":
                item = "views/zavuchdashboard.fxml";
                l = true;
                break;
            case "student":
                item = "views/studentdashboard.fxml";
                l = true;
                break;
            default:
                mistake.setText("Неверный логин/пароль");
                break;
        }

        if (l) {
            mistake.setText("");
            login.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(item));
            stage.setTitle("Shedule");
            stage.setScene(new Scene(root, 1031, 791));
            stage.show();
        }

    }
}
