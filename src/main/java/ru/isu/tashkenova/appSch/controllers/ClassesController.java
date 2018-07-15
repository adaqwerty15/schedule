package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.StudentsClass;
import ru.isu.tashkenova.appSch.UserService;

import java.io.IOException;
import java.util.List;

public class ClassesController {
    @FXML
    private Button add;

   @FXML
    private AnchorPane apane;

    UserService service;
    ObservableList<StudentsClass> contentStudents;



    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String s = "Add class";
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/addClass.fxml"));
        stage.setTitle(s);
        stage.setScene(new Scene(root, 407, 421));
        stage.show();
    }

    @FXML
    void labelClassEntered(MouseEvent mouseEvent) {
        Label l = (Label)mouseEvent.getTarget();
        l.setUnderline(true);
    }

    @FXML
    void labelClassExited(MouseEvent mouseEvent) {
        Label l = (Label)mouseEvent.getTarget();
        l.setUnderline(false);
    }

    @FXML
    void labelClassesClicked(MouseEvent event) {
        Stage stage = new Stage();
        String s = "Class Editor";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/classEditor.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader .load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClassEditorController cec = fxmlLoader.getController();
        try {
            cec.setClassCode(((Label)event.getSource()).getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(s);
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }

    public void initialize() throws IOException {
        int x = 125;
        int y = 190;
        int deltaX = 125;
        int deltaY = 50;

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        service = RetrofitService.RetrofitBuildU();
        Response<List<StudentsClass>> stud = service.getStudetsClasses().execute();

        contentStudents = FXCollections.observableArrayList(
                stud.body()
        );

        int p = 5;
        int firstCourse = 5;
        int c = 0;
        for (StudentsClass s:contentStudents) {
            if (s.course!=firstCourse) {
                c = 0;
                firstCourse = s.course;
            }
            setLabels(x+(s.course - p)*deltaX,y+deltaY*c, s.getCode());
            c++;
        }

    }


    public void setLabels(int x, int y, String text) throws IOException{
        Label cl = new Label(text);
        cl.setLayoutX(x);
        cl.setAlignment(Pos.CENTER);
        cl.setLayoutY(y);
        cl.setFont(new Font("System", 21));
        cl.setTextFill(Paint.valueOf("#228cc9"));
        apane.getChildren().add(cl);
        cl.setOnMouseClicked(this::labelClassesClicked);
        cl.setOnMouseEntered(this::labelClassEntered);
        cl.setOnMouseExited(this::labelClassExited);
    }

    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }
}
