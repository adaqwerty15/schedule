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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SchemeController {

    WorkloadService service;
    ObservableList<Subject> contentSubject;
    ObservableList<User> contentUsers;
    ObservableList<StudentsClass> contentStudents;

    @FXML
    private GridPaneNew gridpane = new GridPaneNew();

    @FXML
    private GridPane gp = new GridPane();

    @FXML
    private Label img;

    @FXML
    private ScrollPane scrolpane;

    @FXML
    private AnchorPane apane;

    @FXML
    private ChoiceBox<String> classchoice = new ChoiceBox<String>();

    public boolean valid = true;
    public int dayId;


    public int rows;
    public int cols;
    public int teacherId;
    public int subjectId;
    public int choiceClassId;
    public String numberSchemaId;
    public String nameSchema;
    public LabelNew[][] labels;

    public void setDayId (int dayId) throws IOException {
        this.dayId = dayId;
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("ddMMyyyyhhmmss");
        numberSchemaId = format2.format(d);
        nameSchema = "Схема от "+ format1.format(d);
        RetrofitService.RetrofitBuildW().addscheduleOwner(new ScheduleOwner(0, numberSchemaId, nameSchema, 0, dayId, valid,0 )).execute();

    }


    public void initialize() throws IOException {

        gp.setPadding(new Insets(10));
        gp.setPrefWidth(100 * 26);
        gp.setVgap(15);


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
        classchoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                choiceClassId = (int)newValue;
            }
        });



        HashMap<Integer, StudentsClass> students = new HashMap<>();
        for (int i=0; i<contentStudents.size(); i++) {
            students.put(contentStudents.get(i).getId(),contentStudents.get(i));
        }


        //        ArrayList<String> a = new ArrayList<String>(Arrays.asList("","5и","5м", "6л", "6м", "7а", "7б", "8а", "8б", "8и", "8л", "9а", "9е", "9и", "9л", "9м",
//                "9э", "10а", "10е", "10и", "10л", "10м", "11е", "11и", "11л", "11м", "11э"));

        ArrayList<String> time = new ArrayList<String>(Arrays.asList("","8.00-8.40", "8.50-9.30", "9.40-10.20", "10.40-11.20", "11.40-12.20", "12.30-13.10", "13.20-14.00",
                "14.10-14.50","15.00-15.40","16.00-16.40", "17.00-17.40", "17.50-18.30", "18.40-19.20"));

        rows = time.size();
        cols = stud.size()+1 ;
        gridpane.init(cols,rows);

        ArrayList<Workload> workload = new ArrayList<>();
        workload.add(new Workload(1, 1, 9));
        workload.add(new Workload(1, 2, 5));
        workload.add(new Workload(1, 3, 21));
        workload.add(new Workload(1, 4, 7));
        workload.add(new Workload(1, 5, 17));
        workload.add(new Workload(1, 5, 26));
        workload.add(new Workload(1, 6, 8));
        workload.add(new Workload(1, 6, 14));
        workload.add(new Workload(1, 7, 25));
        workload.add(new Workload(1, 8, 6));
        workload.add(new Workload(1, 9, 15));
        workload.add(new Workload(1, 10, 12));
        workload.add(new Workload(1, 11, 25));
        workload.add(new Workload(1, 12, 5));
        workload.add(new Workload(1, 13, 5));
        workload.add(new Workload(1, 14, 15));
        workload.add(new Workload(1, 15, 6));
        workload.add(new Workload(1, 16, 8));
        workload.add(new Workload(1, 16, 14));
        workload.add(new Workload(1, 17, 22));
        workload.add(new Workload(1, 18, 4));

        workload.add(new Workload(2, 1, 9));
        workload.add(new Workload(2, 2, 5));
        workload.add(new Workload(2, 3, 21));
        workload.add(new Workload(2, 4, 7));
        workload.add(new Workload(2, 5, 17));
        workload.add(new Workload(2, 5, 26));
        workload.add(new Workload(2, 7, 25));
        workload.add(new Workload(2, 8, 6));
        workload.add(new Workload(2, 9, 15));
        workload.add(new Workload(2, 10, 12));
        workload.add(new Workload(2, 11, 25));
        workload.add(new Workload(2, 12, 5));
        workload.add(new Workload(2, 13, 5));
        workload.add(new Workload(2, 14, 15));
        workload.add(new Workload(2, 15, 6));
        workload.add(new Workload(2, 16, 8));
        workload.add(new Workload(2, 16, 14));
        workload.add(new Workload(2, 17, 22));
        workload.add(new Workload(2, 18, 4));
        workload.add(new Workload(2, 19, 16));






        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean exist = false;
                if (newValue != null) {
                    ArrayList<WorkloadView> workloadViews = new ArrayList<>();
                    for (int i = 0; i < workload.size(); i++) {
                        if ((students.get(workload.get(i).studentClassId)).getCode() == newValue) {
                            exist = true;
                            workloadViews.add(new WorkloadView(students.get(workload.get(i).getStudentClassId()),
                                    subjects.get(workload.get(i).getSubjectId()), users.get(workload.get(i).getUserId())));
                        }
                    }

                    if (!exist) {
                        // apane.getChildren().clear();
                        gp.getChildren().clear();
                    }


                    LabelNew labelsScroll[] = new LabelNew[workloadViews.size()];

                    for (int i = 0; i < workloadViews.size(); i++) {
                        labelsScroll[i] = new LabelNew(workloadViews.get(i).toString(), 0, workload.get(i).userId,
                                true, workload.get(i).subjectId);
                        labelsScroll[i].setFont(new Font("Arial", 15));
                        labelsScroll[i].setPrefWidth(120);
                        labelsScroll[i].setMinHeight(60);
                        labelsScroll[i].setAlignment(Pos.CENTER);
                        labelsScroll[i].setStyle("-fx-background-color:#bfbfbf");
                        gp.add(labelsScroll[i],0,i);


                        //apane.getChildren().add(labelsScroll[i]);

                        final int ci = i;

                        labelsScroll[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {

                                System.out.println("onDragDetected");
                                Dragboard db = labelsScroll[ci].startDragAndDrop(TransferMode.ANY);
                                ClipboardContent content = new ClipboardContent();
                                content.putString(labelsScroll[ci].getText());
                                teacherId = labelsScroll[ci].getTeacherId();
                                subjectId = labelsScroll[ci].getSubjectId();
                                db.setContent(content);

                                event.consume();
                            }
                        });
                    }

                }
            }
            };


        classchoice.getSelectionModel().selectedItemProperty().addListener(changeListener);

        //l1.setText("Литература \nБурзунова Г. Е.");;

        //gridpane.setPadding(new Insets(40));


        gridpane.setGridLinesVisible(true);
        gridpane.setPrefWidth(100*26);
        gridpane.setPadding(new Insets(40));


        labels = new LabelNew[time.size()+1][stud.size()+1];
        //Button[] buttons = new Button[]


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(100);
        columnConstraints.setHalignment(HPos.CENTER);

        labels[0][0] = new LabelNew("");
        labels[0][0].setPrefWidth(gridpane.getPrefWidth());
        labels[0][0].setId("right");
        labels[0][0].setPrefHeight((gridpane.getPrefHeight()));
        labels[0][0].setStyle("#ffffff;-fx-border-width: 0.5; -fx-border-color: #000000");
        gridpane.add( labels[0][0], 0, 0);
        labels[0][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0, 0));

        gridpane.add(new Label(""), 0, 0);
        for(int i = 1; i<stud.size()+1; i++) {
            int selectedCol = i;
            labels[0][i] = new LabelNew(stud.get(i-1));
            labels[0][i].setFont(new Font("Arial", 17));
            labels[0][i].setPrefWidth(gridpane.getPrefWidth());
            labels[0][i].setPrefHeight((gridpane.getPrefHeight()));
            labels[0][i].setAlignment(Pos.CENTER);
            labels[0][i].setId("right");
            labels[0][i].setStyle("-fx-border-width: 0.5; -fx-border-color: #000000");
            gridpane.getColumnConstraints().add(columnConstraints);
            gridpane.add(labels[0][i], i, 0);

            labels[0][i].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(selectedCol, 0));


        }
        gridpane.getColumnConstraints().add(columnConstraints);

        for (int j=1; j<time.size(); j++) {
            int selectedRow = j;
            labels[j][0] = new LabelNew("\n"+time.get(j)+"\n");
            gridpane.add(labels[j][0],0,j);
            labels[j][0].setStyle("-fx-border-width: 0.5; -fx-border-color:#000000");
            labels[j][0].setFont(new Font("Arial", 15));
            labels[j][0].setPrefWidth(gridpane.getPrefWidth());
            labels[j][0].setPrefHeight((gridpane.getPrefHeight()));
            labels[j][0].setAlignment(Pos.CENTER);
            labels[j][0].setId("right");
            labels[j][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0,selectedRow));
        }


        img.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() !=  img &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        img.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                event.consume();
            }
        });

        img.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {

                System.out.println("onDragDropped");

                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
//                    img.setText(db.getString());
//                    img.setStyle("-fx-background-color:#ccc");
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        for(int i = 1; i<time.size(); i++)
            for (int j=1; j<stud.size()+1; j++) {
                int selectedCol = j;
                int selectedRow = i;
                labels[i][j] = new LabelNew("            \n              \n             ");
                labels[i][j].setFont(new Font("Arial", 11));
                labels[i][j].setPrefHeight(gridpane.getPrefHeight());
                labels[i][j].setPrefWidth(gridpane.getPrefWidth());
                labels[i][j].setAlignment(Pos.CENTER);
                labels[i][j].setId("right");
                labels[i][j].setStyle("-fx-border-width: 0.5; -fx-border-color: #000000");
                gridpane.add( labels[i][j],j,i);
                labels[i][j].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(selectedCol, selectedRow));

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

//                            HashSet<Integer> ids  = new HashSet();
//                            ids.add(teacherId);

                                labels[col][row].setTeacherId(teacherId);
                                labels[col][row].setSubjectId(subjectId);
                                for (int i = 1; i < cols; i++) {

                                    if (labels[col][i].getTeacherId() != -1) {
                                        if (labels[col][i].getTeacherId() == teacherId && row != i) {
                                            labels[col][row].setValid(false);
                                        }
                                    }
                                    //System.out.println(labels[col][i]);

                                }

                                if (!labels[col][row].isValid()) {
                                    labels[col][row].setId("wrong");
                                    labels[col][row].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
                                } else labels[col][row].setId("right");

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
                            labels[col][row].setId("right");
                            labels[col][row].setValid(true);
                            labels[col][row].setTeacherId(-1);
                            labels[col][row].setSubjectId(-1);

                            HashSet<Integer> ids  = new HashSet();
                            for (int i = 1; i < cols; i++) {
                                if (labels[col][i].getTeacherId() != -1) {
                                    if (!ids.contains(labels[col][i].getTeacherId())) {
                                        ids.add(labels[col][i].getTeacherId());
                                        labels[col][i].setValid(true);
                                    }
                                    else {
                                        labels[col][i].setValid(false);
                                    }
                                }

                                else {
                                    labels[col][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                                }


                            }

                            for (int i = 1; i < cols; i++) {
                                if (!labels[col][i].isValid()) {
                                    labels[col][i].setId("wrong");
                                    labels[col][i].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
                                } else {
                                    labels[col][i].setId("right");
                                    labels[col][i].setStyle("-fx-background-color:#fff;-fx-border-width:0.5;-fx-border-color:black");
                                }
                            }
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
                        teacherId = labels[col][row].getTeacherId();
                        subjectId = labels[col][row].getSubjectId();
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


    public void saveClick(ActionEvent actionEvent) throws IOException {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
          for(int i=1; i<rows; i++)
              for (int j=1; j<cols;j++) {

              schedules.add(new Schedule(labels[i][j].getSubjectId(),j-1,
                      0, labels[i][j].getTeacherId(), numberSchemaId, i));
              }

        System.out.println(schedules);

        Response<List<Schedule>> schedule = service.getschedule().execute();

        //service.deleteSchedule(numberSchemaId);
        for (Schedule s:schedules) {
            service.addschedule(s).execute();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Изменения сохранены");
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.showAndWait();




    }
}
