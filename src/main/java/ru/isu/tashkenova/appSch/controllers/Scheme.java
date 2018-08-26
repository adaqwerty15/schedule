package ru.isu.tashkenova.appSch.controllers;

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
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class Scheme {
    protected WorkloadService service;
    protected ObservableList<Subject> contentSubject;
    protected ObservableList<User> contentUsers;
    protected ObservableList<StudentsClass> contentStudents;
    protected ObservableList<Workload> contentWorkload;
    protected ObservableList<Cabinet> contentCabinet;

    @FXML
    protected GridPaneNew gridpane = new GridPaneNew();

    @FXML
    protected Label img;

    @FXML
    protected Label excel;

    @FXML
    protected Label pencil;

    @FXML
    protected Label eraser;

    @FXML
    protected Label message;

    @FXML
    protected ChoiceBox<String> classchoice = new ChoiceBox<String>();

    @FXML
    protected GridPane gp = new GridPane();

    @FXML
    protected Pane pane = new Pane();

    @FXML
    protected ScrollPane scrollPane = new ScrollPane();

    @FXML
    protected ScrollPane scrollPane2 = new ScrollPane();

    @FXML
    protected AnchorPane anchorPane = new AnchorPane();

    public boolean valid = true;
    public CreateScheduleView view;
    public ObservableList<Schedule> contentSch;

    protected int rows;
    protected int cols;
    protected int teacherId;
    String content_l;
    protected int subjectId;
    protected int cabinetId;
    public int choiceClassId;
    protected String numberSchemaId;
    protected LabelNew[][] labels;
    protected  HashMap<Integer, Subject> subjects;
    protected  HashMap<Integer, User> users;
    protected HashMap<Integer, Cabinet> cabinets;
    protected boolean pencil_f = false;
    protected boolean eraser_f = false;
    protected Scene scene;

    public void init () throws IOException {




        message.setText("Для добавления кабинета щелкните правой кнопкой мыши по уроку");
        message.setPadding(new Insets(5));
        message.setStyle("-fx-border-color: black;");
        message.setStyle("-fx-border-style: dashed;");

        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                    if (event.getClickCount()==2)
                        cancelPencil();
            }
        });


        img.setTooltip(new Tooltip("Перетащите уроки для удаления"));

        excel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                scene = img.getScene();
                scene.setCursor(Cursor.HAND); //Change cursor to hand
                excel.setTooltip(new Tooltip("Сохранить файл в Excel"));
            }
        });

        excel.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                scene = img.getScene();
                scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
            }
        });


        eraser.setTooltip(new Tooltip("Объединить ячейку"));
        pencil.setTooltip(new Tooltip("Разделить ячейку"));



        gp.setPadding(new Insets(10));
        gp.setPrefWidth(200);
        gp.setVgap(15);

        service = RetrofitService.RetrofitBuildW();

        Response<List<User>> users_s = service.getUsers().execute();
        contentUsers = FXCollections.observableArrayList(
                users_s.body()
        );

        Response<List<Cabinet>> cabinet_s = service.getCabinet().execute();
        contentCabinet = FXCollections.observableArrayList(
                cabinet_s.body()
        );

        cabinets = new HashMap<>();
        for (int i = 0; i < contentCabinet.size(); i++) {
            cabinets.put(contentCabinet.get(i).getId(), contentCabinet.get(i));
        }

        users = new HashMap<>();
        for (int i = 0; i < contentUsers.size(); i++) {
            users.put(contentUsers.get(i).getId(), contentUsers.get(i));
        }

        Response<List<Subject>> subjects_s = service.getSubjects().execute();
        contentSubject = FXCollections.observableArrayList(
                subjects_s.body()
        );

        subjects = new HashMap<>();
        for (int i = 0; i < contentSubject.size(); i++) {
            subjects.put(contentSubject.get(i).getId(), contentSubject.get(i));
        }

        Response<List<StudentsClass>> stud_s = service.getStudentClasses().execute();
        contentStudents = FXCollections.observableArrayList(
                stud_s.body()
        );

        ArrayList<String> stud = new ArrayList<String>();
        for (StudentsClass s : contentStudents)
            stud.add(s.getCode());
        classchoice.setItems(FXCollections.observableArrayList(stud));
        classchoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                choiceClassId = (int) newValue;
            }
        });


        HashMap<Integer, StudentsClass> students = new HashMap<>();
        for (int i = 0; i < contentStudents.size(); i++) {
            students.put(contentStudents.get(i).getId(), contentStudents.get(i));
        }

        ArrayList<String> time = new ArrayList<String>(Arrays.asList("", "8.00-8.40", "8.50-9.30", "9.40-10.20", "10.40-11.20", "11.40-12.20", "12.30-13.10", "13.20-14.00",
                "14.10-14.50", "15.00-15.40", "16.00-16.40", "17.00-17.40", "17.50-18.30", "18.40-19.20"));



        Response<List<Workload>> workload_s = service.getWorkload().execute();
        contentWorkload = FXCollections.observableArrayList(
                workload_s.body()
        );

        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean exist = false;
                if (newValue != null) {
                    ArrayList<WorkloadView> workloadViews = new ArrayList<>();
                    for (int i = 0; i < contentWorkload.size(); i++) {
                        if ((students.get(contentWorkload.get(i).studentClassId)).getCode() == newValue) {
                            gp.getChildren().clear();
                            exist = true;
                            workloadViews.add(new WorkloadView(students.get(contentWorkload.get(i).getStudentClassId()),
                                    subjects.get(contentWorkload.get(i).getSubjectId()), users.get(contentWorkload.get(i).getUserId())));
                        }
                    }

                    if (!exist) {
                        gp.getChildren().clear();
                    }


                    LabelNew labelsScroll[] = new LabelNew[workloadViews.size()];

                    for (int i = 0; i < workloadViews.size(); i++) {
                        labelsScroll[i] = new LabelNew(workloadViews.get(i).toString(), -1, workloadViews.get(i).getUser().getId(),
                                true,  workloadViews.get(i).getSubject().getId());

                        labelsScroll[i].setFont(new Font("Arial", 15));
                        labelsScroll[i].setPrefWidth(140);
                        labelsScroll[i].setMinHeight(60);
                        labelsScroll[i].setAlignment(Pos.CENTER);
                        labelsScroll[i].setStyle("-fx-background-color:#bfbfbf");
                        gp.add(labelsScroll[i], 0, i);

                        final int ci = i;

                        labelsScroll[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {
                                Dragboard db = labelsScroll[ci].startDragAndDrop(TransferMode.ANY);
                                ClipboardContent content = new ClipboardContent();
                                content.putString(labelsScroll[ci].getText());
                                teacherId = labelsScroll[ci].getTeacherId();
                                subjectId = labelsScroll[ci].getSubjectId();
                                cabinetId = -1;
                                db.setContent(content);
                                event.consume();
                            }
                        });

//                        labelsScroll[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
//                            public void handle(MouseEvent event) {
//                                content_l = labelsScroll[ci].getText();
//                                teacherId = labelsScroll[ci].getTeacherId();
//                                subjectId = labelsScroll[ci].getSubjectId();
//                                cabinetId = -1;
//                                labelsScroll[ci].setStyle("-fx-background-color:#99bbff");
//                                event.consume();
//                            }
//                        });
                    }

                }
            }
        };


        classchoice.getSelectionModel().selectedItemProperty().addListener(changeListener);

        rows = time.size();
        cols = stud.size() + 2;
        gridpane.init(cols, rows*2-1);

        gridpane.setGridLinesVisible(true);
        gridpane.setPrefWidth(100*27);
        gridpane.setPadding(new Insets(40));


        labels = new LabelNew[time.size()*2-1][cols];

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(100);
        columnConstraints.setHalignment(HPos.CENTER);
        //gridpane.getColumnConstraints().add(columnConstraints);


        labels[0][0] = new LabelNew("№");
        labels[0][0].setMaxWidth(30);
        labels[0][0].setMinWidth(30);
        labels[0][0].setPrefWidth(gridpane.getPrefWidth());
        labels[0][0].setId("right");
        labels[0][0].setPrefHeight((gridpane.getPrefHeight()));
        labels[0][0].setAlignment(Pos.CENTER);
        labels[0][0].setStyle("#ffffff;-fx-border-width: 0.5; -fx-border-color: #000000");
        gridpane.add(labels[0][0], 0, 0);

        labels[0][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0, 0));




        labels[0][1] = new LabelNew("Время");
        labels[0][1].setPrefWidth(gridpane.getPrefWidth());
        labels[0][1].setId("right");
        labels[0][1].setPrefHeight((gridpane.getPrefHeight()));
        labels[0][1].setAlignment(Pos.CENTER);
        labels[0][1].setStyle("#ffffff;-fx-border-width: 0.5; -fx-border-color: #000000");
        gridpane.add(labels[0][1], 1, 0);

        labels[0][1].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(1, 0));


        for (int i = 2; i < cols; i++) {
            int selectedCol = i;
            labels[0][i] = new LabelNew(stud.get(i - 2));
            labels[0][i].setFont(new Font("Arial", 17));
            labels[0][i].setPrefWidth(gridpane.getPrefWidth());
            labels[0][i].setPrefHeight((gridpane.getPrefHeight()));
            labels[0][i].setAlignment(Pos.CENTER);
            labels[0][i].setId("right");
            labels[0][i].setStyle("-fx-border-width: 0.5; -fx-border-color: #000000");
            //gridpane.getColumnConstraints().add(columnConstraints);
            gridpane.add(labels[0][i], i, 0);
            labels[0][i].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(selectedCol, 0));
        }


        for (int j = 1; j < time.size(); j++) {
            int selectedRow = j*2-1;
            labels[j*2-1][0] = new LabelNew(String.valueOf(j%7));
            labels[j*2][0] = new LabelNew("");
            gridpane.add(labels[j*2][0], 0, j*2);
            gridpane.add(labels[j*2-1][0], 0, j*2-1);
            gridpane.setRowSpan(labels[j*2-1][0],2);
            labels[j*2-1][0].setStyle("-fx-border-width: 0.5; -fx-border-color:#000000");
            labels[j*2-1][0].setFont(new Font("Arial", 15));
            labels[j*2-1][0].setPrefWidth(gridpane.getPrefWidth());
            labels[j*2-1][0].setMaxWidth(30);
            labels[j*2-1][0].setMinWidth(30);
            labels[j*2-1][0].setPrefHeight(gridpane.getPrefHeight());
            labels[j*2-1][0].setMinHeight(55);
            labels[j*2-1][0].setAlignment(Pos.CENTER);
            labels[j*2-1][0].setId("right");
            labels[j*2][0].setId("right");
            labels[j*2-1][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0, selectedRow));

        }


        for (int j = 1; j < time.size(); j++) {
            int selectedRow = j*2-1;
            labels[j*2-1][1] = new LabelNew(time.get(j));
            labels[j*2][1] = new LabelNew("");
            gridpane.add(labels[j*2][1], 1, j*2);
            gridpane.add(labels[j*2-1][1], 1, j*2-1);
            gridpane.setRowSpan(labels[j*2-1][1],2);
            labels[j*2-1][1].setStyle("-fx-border-width: 0.5; -fx-border-color:#000000");
            labels[j*2-1][1].setFont(new Font("Arial", 15));
            labels[j*2-1][1].setPrefWidth(gridpane.getPrefWidth());
            labels[j*2-1][1].setPrefHeight(gridpane.getPrefHeight());
            labels[j*2-1][1].setMinHeight(55);
            labels[j*2-1][1].setAlignment(Pos.CENTER);
            labels[j*2-1][1].setId("right");
            labels[j*2][1].setId("right");
            labels[j*2-1][1].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(1, selectedRow));

        }


        img.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != img &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        img.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.consume();
            }
        });

        img.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString())
                    success = true;
                event.setDropCompleted(success);
                event.consume();
            }
        });

        ContextMenu contextMenu = new ContextMenu();

        ArrayList<MenuItem> items = new ArrayList<>();

        MenuItem item0 = new MenuItem("Удалить урок");
        item0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((LabelNew) contextMenu.getOwnerNode()).setText("");
                ((LabelNew) contextMenu.getOwnerNode()).setTeacherId(-1);
                ((LabelNew) contextMenu.getOwnerNode()).setSubjectId(-1);
                ((LabelNew) contextMenu.getOwnerNode()).setCabinetId(-1, 0);
                checkAll();
            }
        });


        contextMenu.getItems().add(item0);

        SeparatorMenuItem sep = new SeparatorMenuItem();
        contextMenu.getItems().add(sep);

        MenuItem item = new MenuItem("Нет кабинета");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((LabelNew) contextMenu.getOwnerNode()).clearCabinet();
                ((LabelNew) contextMenu.getOwnerNode()).setCabinetId(-1, 0);
                checkAll();
            }
        });


        contextMenu.getItems().add(item);

        for (Cabinet c : contentCabinet) {
            MenuItem item1 = new MenuItem(c.getName());
            item1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((LabelNew) contextMenu.getOwnerNode()).addCabinet(c.getName());
                    ((LabelNew) contextMenu.getOwnerNode()).setCabinetId(c.getId(), c.getName().length());
                    checkAll();
                }
            });
            contextMenu.getItems().add(item1);
        }

        for (int i = 1; i < time.size(); i++)
            for (int j = 2; j < cols; j++) {
                int selectedCol = j;
                int selectedRow = i * 2 - 1;
                labels[i * 2 - 1][j] = new LabelNew("            \n              \n             \n  ");
                labels[i * 2][j] = new LabelNew("");
                gridpane.add(labels[i * 2][j], j, i * 2);
                gridpane.add(labels[i * 2 - 1][j], j, i * 2 - 1);
                gridpane.setRowSpan(labels[i * 2 - 1][j], 2);
                labels[i * 2 - 1][j].setFont(new Font("Arial", 11));
                labels[i*2-1][j].setMinHeight(55);
                labels[i*2][j].setMinHeight(55);
                labels[i*2-1][j].setPrefHeight(gridpane.getPrefHeight());
                labels[i * 2 - 1][j].setPrefWidth(gridpane.getPrefWidth());
                labels[i * 2 - 1][j].setAlignment(Pos.CENTER);
                labels[i * 2 - 1][j].setId("right");
                labels[i * 2][j].setId("right");
                labels[i * 2 - 1][j].setStyle("-fx-border-width: 0.5; -fx-border-color: #000000");
                labels[i * 2 - 1][j].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(selectedCol, selectedRow));
            }


        for (int i = 1; i < time.size()*2-1; i++)
            for (int j = 2; j < cols; j++) {

                final int col = i;
                final int row = j;

                labels[i][j].setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle(ContextMenuEvent event) {
                        contextMenu.show(labels[col][row], event.getScreenX(), event.getScreenY());
                    }
                });


                Tooltip t = new Tooltip("№ урока:"+String.valueOf(((i+1)/2)%7)
                        +"\nКласс: "+labels[0][row].getText());
                t.setFont(new Font("Arial", 15));

                labels[col][row].setTooltip(t);

                labels[col][row].setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        if (event.getGestureSource() != labels[col][row] &&
                                event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }
                        event.consume();
                    }
                });

                labels[col][row].setOnDragExited(event -> event.consume());

                labels[col][row].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                    }
                });

                labels[col][row].setOnDragDropped(event -> {

                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        labels[col][row].setText(db.getString());
                        labels[col][row].setTeacherId(teacherId);
                        labels[col][row].setSubjectId(subjectId);


                        if (cabinetId!=-1)
                            labels[col][row].setCabinetId(cabinetId, cabinets.get(cabinetId).getName().length());
                        else
                            labels[col][row].setCabinetId(cabinetId,0) ;

                        int k1;
                        int k2;

                        if (col % 2 != 0) {
                             k1 = col;
                            k2 = col + 1;
                        }
                        else {
                            k1 = col-1;
                            k2 = col;
                        }
                            for (int i1 = 2; i1 < cols; i1++) {
                                for (int j1 = k1; j1<=k2; j1++)
                                    if ((labels[j1][i1].getTeacherId() == teacherId && labels[j1][i1].getTeacherId() != -1
                                            ||  labels[j1][i1].getCabinetId() == cabinetId && labels[j1][i1].getCabinetId() != -1)
                                            && (row != i1 || col!=j1)) {
                                        labels[col][row].setValid(false);
                                    }
                            }

                        if (!labels[col][row].isValid()) {
                            labels[col][row].setId("wrong");
                            labels[col][row].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
                        } else labels[col][row].setId("right");

                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                });

                labels[col][row].setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {

                        if (event.getTransferMode() == TransferMode.MOVE) {
                            labels[col][row].setText("");
                            labels[col][row].setId("right");
                            labels[col][row].setValid(true);
                            labels[col][row].setTeacherId(-1);
                            labels[col][row].setSubjectId(-1);
                            labels[col][row].setCabinetId(-1, 0);

                            HashSet<Integer> ids = new HashSet();

                            int k1;
                            int k2;
                            if (col % 2 != 0) {
                                k1 = col;
                                k2 = col + 1;
                            }
                            else {
                                k1 = col-1;
                                k2 = col;
                            }

                            for (int i = 2; i < cols; i++)
                                for (int j1 = k1; j1<=k2; j1++){
                                if (labels[j1][i].getTeacherId() != -1) {
                                    if (!ids.contains(labels[j1][i].getTeacherId())) {
                                        ids.add(labels[j1][i].getTeacherId());
                                        labels[j1][i].setValid(true);
                                    } else {
                                        labels[j1][i].setValid(false);
                                    }
                                } else {
                                    labels[j1][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                                }
                            }



                            HashSet<Integer> idCab = new HashSet();
                            for (int i = 2; i < cols; i++)
                                for (int j1 = k1; j1<=k2; j1++) {
                                if (labels[j1][i].isValid()) {
                                    if (labels[j1][i].getCabinetId() != -1) {
                                        if (!idCab.contains(labels[j1][i].getCabinetId())) {
                                            idCab.add(labels[j1][i].getCabinetId());
                                            labels[j1][i].setValid(true);
                                        } else {
                                            labels[j1][i].setValid(false);
                                        }
                                    } else {
                                        labels[j1][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                                    }
                                }
                            }

                            for (int i = 2; i < cols; i++)
                                for (int j1 = k1; j1<=k2; j1++){
                                if (!labels[j1][i].isValid()) {
                                    labels[j1][i].setId("wrong");
                                    labels[j1][i].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
                                } else {
                                    labels[j1][i].setId("right");
                                    labels[j1][i].setStyle("-fx-background-color:#fff;-fx-border-width:0.5;-fx-border-color:black");
                                }
                            }
                        }

                        event.consume();
                    }
                });

                labels[col][row].setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        Dragboard db = labels[col][row].startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(labels[col][row].getText());
                        teacherId = labels[col][row].getTeacherId();
                        subjectId = labels[col][row].getSubjectId();
                        cabinetId = labels[col][row].getCabinetId();
                        db.setContent(content);
                        event.consume();
                    }
                });

                labels[col][row].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if ((pencil_f && (col%2==0 || gridpane.getRowSpan(labels[col][row])==1)) ||
                                (eraser_f &&  gridpane.getRowSpan(labels[col][row])==2)) {
                           cancelPencil();
                        }

                        if (pencil_f && col % 2 != 0) {
                            gridpane.setRowSpan(labels[col][row], 1);
                            labels[col][row].setStyle("-fx-border-width: 0.5; -fx-border-color:#000000");
                            labels[col+1][row].setFont(new Font("Arial", 11));
                            labels[col+1][row].setPrefWidth(gridpane.getPrefWidth());
                            labels[col+1][row].setPrefHeight((gridpane.getPrefHeight()));
                            labels[col+1][row].setMinHeight(55);
                            labels[col+1][row].setAlignment(Pos.CENTER);
                            labels[col+1][row].setId("right");
                            labels[col+1][row].setOnMouseMoved((EventHandler) event2 -> gridpane.selectRange(row, col));
                        }
                        else if (eraser_f) {
                            if (col%2!=0) {
                                gridpane.setRowSpan(labels[col][row], 2);
                                labels[col+1][row].setText("");
                                labels[col][row].setMinHeight(55);
                                labels[col+1][row].setMinHeight(55);
                                labels[col][row].setPrefHeight(gridpane.getPrefHeight());
                                labels[col][row].setPrefWidth(gridpane.getPrefWidth());
                                labels[col+1][row].setId("right");
                            }
                            else {
                                gridpane.setRowSpan(labels[col-1][row], 2);
                                labels[col][row].setText("");
                                labels[col-1][row].setMinHeight(55);
                                labels[col][row].setMinHeight(55);
                                labels[col-1][row].setPrefHeight(gridpane.getPrefHeight());
                                labels[col-1][row].setPrefWidth(gridpane.getPrefWidth());
                                labels[col][row].setId("right");
                            }
                        }


                    }
                });
            }


        anchorPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ESCAPE))
                   cancelPencil();
            }
        });
    }

    public void paneClicked(MouseEvent mouseEvent) {
        cancelPencil();
    }

    public void cancelPencil() {
        scene = img.getScene();
        scene.setCursor(Cursor.DEFAULT);
        pencil_f = false;
        eraser_f = false;
        message.setText("Для добавления кабинета щелкните правой кнопкой мыши по уроку");
        eraser.setStyle("-fx-background-color: none;");
        pencil.setStyle("-fx-background-color: none;");
    }


    public void checkAll() {


        for (int j = 1; j < rows; j+=2) {

            HashSet<Integer> ids = new HashSet();
            for (int i = 2; i < cols; i++)
                for (int k = j; k <= j+1; k++)   {
                if (labels[k][i].getTeacherId() != -1) {
                    if (!ids.contains(labels[k][i].getTeacherId())) {
                        ids.add(labels[k][i].getTeacherId());
                        labels[k][i].setValid(true);
                    } else {
                        labels[k][i].setValid(false);
                    }
                } else {
                    labels[k][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                }
            }


            HashSet<Integer> idCab = new HashSet();
            for (int i = 2; i < cols; i++) {
                for (int k = j; k <= j+1; k++)
                if (labels[k][i].isValid()) {
                    if (labels[k][i].getCabinetId() != -1) {
                        if (!idCab.contains(labels[k][i].getCabinetId())) {
                            idCab.add(labels[k][i].getCabinetId());
                            labels[k][i].setValid(true);
                        } else {
                            labels[k][i].setValid(false);
                        }
                    } else {
                        labels[k][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                    }
                }
            }


        }

        for (int j = 1; j < rows; j++)
        for (int i = 2; i < cols; i++) {
            if (!labels[j][i].isValid()) {
                labels[j][i].setId("wrong");
                labels[j][i].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
            } else {
                labels[j][i].setId("right");
                labels[j][i].setStyle("-fx-background-color:#fff;-fx-border-width:0.5;-fx-border-color:black");
            }
        }

    }

    public void saveClick (ActionEvent actionEvent) throws IOException {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (int i = 1; i < rows*2-1; i++)
            for (int j = 2; j < cols; j++) {
                if (labels[i][j].getSubjectId() != -1)
                    schedules.add(new Schedule(labels[i][j].getSubjectId(), j - 2,
                            labels[i][j].getCabinetId(), labels[i][j].getTeacherId(), numberSchemaId, i));
            }

        service.deleteSchedule(numberSchemaId).execute();
        for (Schedule s : schedules) {
            service.addschedule(s).execute();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Изменения сохранены");
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.showAndWait();

    }

    public void saveExcel(MouseEvent mouseEvent) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Расписание");

        org.apache.poi.ss.usermodel.Row row;
        spreadsheet.setDefaultRowHeight((short)500);
        spreadsheet.setDefaultColumnWidth(15);


        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontHeightInPoints((short)8);
        font.setFontName("Tahoma");
        style.setFont(font);

        for (int i = 0; i < rows*2-1; i++) {
            row = spreadsheet.createRow(i);
            CellRangeAddress cellRangeAddress;
            for (int j = 0; j < cols; j++) {
                if (i%2!=0 && gridpane.getRowSpan(labels[i][j]) == 2) {
                    cellRangeAddress = new CellRangeAddress(i, i+1, j, j);
                    spreadsheet.addMergedRegion(cellRangeAddress);
                }
                Cell cell = row.createCell(j);
                //cell.setCellValue(labels[i][j].getText());
                if (labels[i][j].getSubjectId()==-1) cell.setCellValue(labels[i][j].getText());
                else if (labels[i][j].getCabinetId()!=-1)
                cell.setCellValue(subjects.get(labels[i][j].getSubjectId()).getShortName()+"\n"+
                        cabinets.get(labels[i][j].getCabinetId()).getName());
                else cell.setCellValue(subjects.get(labels[i][j].getSubjectId()).getShortName());
                cell.setCellStyle(style);
            }
        }

        File folder = new File("D:\\Schedule");

        String[] files = folder.list(new FilenameFilter() {

            @Override public boolean accept(File folder, String name) {
                return name.endsWith(".xls");
            }

        });

        ArrayList<String> filesxls = new ArrayList<String>(Arrays.asList(files));

        String name = "raspisanie"+numberSchemaId;

        for ( ;filesxls.contains(name+".xls"); name+="(1)" ) {}


        FileOutputStream fileOut =  new FileOutputStream("D:\\Schedule\\"+name+".xls");
        workbook.write(fileOut);
        fileOut.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Файл сохранен D:\\Schedule");
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.showAndWait();
    }

    public void eraserClicked(MouseEvent mouseEvent) {
        if (!eraser_f) {
            Image image = new Image("views/eraser_small.png");
            scene = img.getScene();
            scene.setCursor(new ImageCursor(image));
            eraser.setStyle("-fx-background-color: #99bbff;");
            pencil.setStyle("-fx-background-color: none;");
            eraser_f = true;
            pencil_f = false;
            message.setText("Для отмены нажмите Esc");
        }

        else cancelPencil();


    }

    public void pencilClicked(MouseEvent mouseEvent) {
        if (!pencil_f) {
            Image image = new Image("views/pencil_small.png");
            scene = img.getScene();
            scene.setCursor(new ImageCursor(image));
            pencil.setStyle("-fx-background-color: #99bbff;");
            eraser.setStyle("-fx-background-color: none;");
            pencil_f = true;
            eraser_f = false;
            message.setText("Для отмены нажмите Esc");
        }

        else cancelPencil();

    }

}
