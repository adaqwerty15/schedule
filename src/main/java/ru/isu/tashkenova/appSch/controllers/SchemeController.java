package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
import java.util.*;


public class SchemeController {

    WorkloadService service;
    ObservableList<Subject> contentSubject;
    ObservableList<User> contentUsers;
    ObservableList<StudentsClass> contentStudents;

    @FXML
    private GridPane gridpane = new GridPane();

    @FXML
    private TableView scheme;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private ScrollPane scrolpane;

    @FXML
    private AnchorPane apane;

    @FXML
    private ChoiceBox classchoice;

    private int x = 1;
    private int y = 1;


    public void initialize() throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        service = RetrofitService.RetrofitBuildW();

        Response<List<User>> users_s = service.getUsers().execute();
        contentUsers = FXCollections.observableArrayList(
                users_s.body()
        );

        HashMap<Integer, User> users = new HashMap<>();
        for (int i=0; i<contentUsers.size(); i++) {
            users.put(contentUsers.get(i).getId(),contentUsers.get(i));
        }

        Response<List<Subject>> subjects_s = service.getSubjects().execute();
        contentSubject = FXCollections.observableArrayList(
                subjects_s.body()
        );

        HashMap<Integer, Subject> subjects = new HashMap<>();
        for (int i=0; i<contentSubject.size(); i++) {
            subjects.put(contentSubject.get(i).getId(),contentSubject.get(i));
        }

        Response<List<StudentsClass>> stud_s = service.getStudentClasses().execute();
        contentStudents = FXCollections.observableArrayList(
                stud_s.body()
        );

        ArrayList<String> stud = new ArrayList<String>();
        for(StudentsClass s: contentStudents)
            stud.add(s.getCode());
        classchoice.setItems(FXCollections.observableArrayList(stud));
        classchoice.getSelectionModel().getSelectedIndexProperty.



        HashMap<Integer, StudentsClass> students = new HashMap<>();
        for (int i=0; i<contentStudents.size(); i++) {
            students.put(contentStudents.get(i).getId(),contentStudents.get(i));
        }


        //        ArrayList<String> a = new ArrayList<String>(Arrays.asList("","5и","5м", "6л", "6м", "7а", "7б", "8а", "8б", "8и", "8л", "9а", "9е", "9и", "9л", "9м",
//                "9э", "10а", "10е", "10и", "10л", "10м", "11е", "11и", "11л", "11м", "11э"));

        ArrayList<String> time = new ArrayList<String>(Arrays.asList("","8.00-8.40", "8.50-9.30", "9.40-10.20", "10.40-11.20", "11.40-12.20", "12.30-13.10", "13.20-14.00",
                "14.10-14.50","15.00-15.40","16.00-16.40", "17.00-17.40", "17.50-18.30", "18.40-19.20"));

        ArrayList<Workload> workload = new ArrayList<>();
        workload.add(new Workload(1,1, 9));
        workload.add(new Workload(1,9, 15));
        workload.add(new Workload(2,1, 9));
        workload.add(new Workload(2,9, 15));
        workload.add(new Workload(2,19, 16));


        ArrayList<WorkloadView> workloadViews = new ArrayList<>();
        for (int i=0; i<workload.size(); i++) {
            workloadViews.add(new WorkloadView(students.get(workload.get(i).getStudentClassId()),
                    subjects.get(workload.get(i).getSubjectId()),users.get(workload.get(i).getUserId())));
        }

        Label labelsScroll[] = new Label[workloadViews.size()];

        for (int i=0; i<workloadViews.size(); i++) {
            labelsScroll[i] = new Label(workloadViews.get(i).toString());
            labelsScroll[i].setStyle("-fx-background-color:#ccc");
            labelsScroll[i].setLayoutX(10);
            labelsScroll[i].setLayoutY(50*i+10);
            apane.getChildren().add(labelsScroll[i]);
            System.out.println(labelsScroll[0].getPrefHeight());
            final int ci = i;

            labelsScroll[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {

                    System.out.println("onDragDetected");
                    Dragboard db = labelsScroll[ci].startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(labelsScroll[ci].getText());
                    db.setContent(content);

                    event.consume();
                }
            });
        }




        //l1.setText("Литература \nБурзунова Г. Е.");;

        //gridpane.setPadding(new Insets(40));


        gridpane.setGridLinesVisible(true);
        gridpane.setPrefWidth(100*26);
        gridpane.setPadding(new Insets(40));


        Label[][] labels = new Label[time.size()+1][stud.size()+1];
        //Button[] buttons = new Button[]


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(100);

        gridpane.add(new Label(""), 0, 0);
        for(int i = 1; i<stud.size()+1; i++) {
            labels[0][i] = new Label(stud.get(i-1));
            gridpane.getColumnConstraints().add(columnConstraints);
            gridpane.add(labels[0][i], i, 0);


        }
        gridpane.getColumnConstraints().add(columnConstraints);

        for (int j=1; j<time.size(); j++) {
            labels[j][0] = new Label("\n"+time.get(j)+"\n");
            gridpane.add(labels[j][0],0,j);
        }

        for(int i = 1; i<time.size(); i++)
            for (int j=1; j<stud.size()+1; j++) {
                labels[i][j] = new Label("            \n              \n             ");
                labels[i][j].setFont(new Font(12));
                gridpane.add( labels[i][j],j,i);

                final int col = i;
                final int row = j;

                labels[col][row].setOnDragOver(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        if (event.getGestureSource() !=  labels[col][row] &&
                                event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });

                labels[col][row].setOnDragExited(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        event.consume();
                    }
                });

                labels[col][row].setOnDragDropped(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {

                        System.out.println("onDragDropped");

                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString()) {
                            labels[col][row].setText(db.getString());
                            labels[col][row].setStyle("-fx-background-color:#ccc");
                            success = true;
                        }

                        event.setDropCompleted(success);

                        event.consume();
                    }
                });

                labels[col][row].setOnDragDone(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {

                        System.out.println("onDragDone");

                        if (event.getTransferMode() == TransferMode.MOVE) {
                            labels[col][row].setText("");
                            labels[col][row].setStyle("-fx-background-color:#fff");
                        }

                        event.consume();
                    }
                });

                labels[col][row].setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        System.out.println("onDragDetected");
                        Dragboard db = labels[col][row].startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(labels[col][row].getText());
                        db.setContent(content);

                        event.consume();
                    }
                });
            }








////        TableColumn[] rows = new TableColumn[a.size()];
////        TableCell t = new TableCell();
//
////         for(int i = 0; i<a.size(); i++) {
////             rows[i] = new TableColumn(a.get(i));
////             //rows[i].setMinWidth(200);
////             scheme.getColumns().addAll(rows[i]);
//
//        // }


    }


}
