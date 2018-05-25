package ru.isu.tashkenova.appSch.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClassesController {


    @FXML
    public void labelClassesClicked(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        String s = "Class Editor";
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/classEditor.fxml"));
        stage.setTitle(s);
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void labelClassEntered(MouseEvent mouseEvent) {
        Label l = (Label)mouseEvent.getTarget();
        l.setUnderline(true);
    }

    @FXML
    public void labelClassExited(MouseEvent mouseEvent) {
        Label l = (Label)mouseEvent.getTarget();
        l.setUnderline(false);
    }

    @FXML
    public void addButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        String s = "Add new class";
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/add.fxml"));
        stage.setTitle(s);
        stage.setScene(new Scene(root, 428, 298));
        stage.show();
    }
}
