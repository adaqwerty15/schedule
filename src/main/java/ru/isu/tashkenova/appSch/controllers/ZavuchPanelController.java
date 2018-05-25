package ru.isu.tashkenova.appSch.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ZavuchPanelController {

    @FXML
    private Label l;

    @FXML
    void buttonExitClick(ActionEvent event)  throws Exception{
        l.getScene().getWindow().hide();
        Stage stage_in = new Stage();
        Parent root_in = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));
        stage_in.setTitle("Shedule");
        stage_in.setScene(new Scene(root_in, 288, 276));
        stage_in.show();

    }

    @FXML
    void buttonClassesClick(ActionEvent event)  throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/classes.fxml"));
        stage.setTitle("Classes");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonSubjectClick(ActionEvent actionEvent)  throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/subjects.fxml"));
        stage.setTitle("Subjects");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonTeachersClick(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/teachers.fxml"));
        stage.setTitle("Teachers");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonCabinetsClick(ActionEvent actionEvent) throws Exception  {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/cabinets.fxml"));
        stage.setTitle("Cabinets");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonRingsClick(ActionEvent actionEvent)  throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/rings.fxml"));
        stage.setTitle("Rings");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonChangeClick (ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/change.fxml"));
        stage.setTitle("Change schedule");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    @FXML
    public void buttonViewClick(ActionEvent actionEvent)  throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/view.fxml"));
        stage.setTitle("View schedule");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

}
