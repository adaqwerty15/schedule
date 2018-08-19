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
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.*;

import java.io.FileOutputStream;
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
    protected ChoiceBox<String> classchoice = new ChoiceBox<String>();

    @FXML
    protected GridPane gp = new GridPane();

    public boolean valid = true;
    public CreateScheduleView view;
    public ObservableList<Schedule> contentSch;

    protected int rows;
    protected int cols;
    protected int teacherId;
    protected int subjectId;
    protected int cabinetId;
    public int choiceClassId;
    protected String numberSchemaId;
    protected LabelNew[][] labels;
    protected  HashMap<Integer, Subject> subjects;
    protected  HashMap<Integer, User> users;
    protected HashMap<Integer, Cabinet> cabinets;

    public void init () throws IOException {
        gp.setPadding(new Insets(10));
        gp.setPrefWidth(100 * 26);
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

        rows = time.size();
        cols = stud.size() + 1;
        gridpane.init(cols, rows);

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
                        labelsScroll[i] = new LabelNew(workloadViews.get(i).toString(), -1, contentWorkload.get(i).userId,
                                true, contentWorkload.get(i).subjectId);
                        labelsScroll[i].setFont(new Font("Arial", 15));
                        labelsScroll[i].setPrefWidth(120);
                        labelsScroll[i].setMinHeight(60);
                        labelsScroll[i].setAlignment(Pos.CENTER);
                        labelsScroll[i].setStyle("-fx-background-color:#bfbfbf");
                        gp.add(labelsScroll[i], 0, i);

                        final int ci = i;

                        labelsScroll[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {

                                System.out.println("onDragDetected");
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
                    }

                }
            }
        };


        classchoice.getSelectionModel().selectedItemProperty().addListener(changeListener);
        gridpane.setGridLinesVisible(true);
        gridpane.setPrefWidth(100 * 26);
        gridpane.setPadding(new Insets(40));


        labels = new LabelNew[time.size() + 1][stud.size() + 1];

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(100);
        columnConstraints.setHalignment(HPos.CENTER);

        labels[0][0] = new LabelNew("");
        labels[0][0].setPrefWidth(gridpane.getPrefWidth());
        labels[0][0].setId("right");
        labels[0][0].setPrefHeight((gridpane.getPrefHeight()));
        labels[0][0].setStyle("#ffffff;-fx-border-width: 0.5; -fx-border-color: #000000");
        gridpane.add(labels[0][0], 0, 0);
        gridpane.add(new LabelNew("kkk"), 0, 0);
        gridpane.setColumnSpan(labels[0][0], 2);
        labels[0][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0, 0));

        gridpane.add(new Label(""), 0, 0);
        for (int i = 1; i < stud.size() + 1; i++) {
            int selectedCol = i;
            labels[0][i] = new LabelNew(stud.get(i - 1));
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

        for (int j = 1; j < time.size(); j++) {
            int selectedRow = j;
            labels[j][0] = new LabelNew(time.get(j));
            gridpane.add(labels[j][0], 0, j);
            labels[j][0].setStyle("-fx-border-width: 0.5; -fx-border-color:#000000");
            labels[j][0].setFont(new Font("Arial", 15));
            labels[j][0].setPrefWidth(gridpane.getPrefWidth());
            labels[j][0].setPrefHeight((gridpane.getPrefHeight()));
            labels[j][0].setMinHeight(55);
            labels[j][0].setAlignment(Pos.CENTER);
            labels[j][0].setId("right");
            labels[j][0].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(0, selectedRow));
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
                System.out.println("onDragDropped");
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
            for (int j = 1; j < stud.size() + 1; j++) {
                int selectedCol = j;
                int selectedRow = i;
                labels[i][j] = new LabelNew("            \n              \n             \n  ");
                labels[i][j].setFont(new Font("Arial", 11));
                labels[i][j].setPrefHeight(gridpane.getPrefHeight());
                labels[i][j].setPrefWidth(gridpane.getPrefWidth());
                labels[i][j].setAlignment(Pos.CENTER);
                labels[i][j].setId("right");
                labels[i][j].setStyle("-fx-border-width: 0.5; -fx-border-color: #000000");
                gridpane.add(labels[i][j], j, i);
                labels[i][j].setOnMouseMoved((EventHandler) event -> gridpane.selectRange(selectedCol, selectedRow));

                final int col = i;
                final int row = j;

                labels[i][j].setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                    @Override
                    public void handle(ContextMenuEvent event) {
                        contextMenu.show(labels[col][row], event.getScreenX(), event.getScreenY());
                    }
                });

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

                        for (int i1 = 1; i1 < cols; i1++) {

                            if ((labels[col][i1].getTeacherId() == teacherId && labels[col][i1].getTeacherId() != -1
                                    ||  labels[col][i1].getCabinetId() == cabinetId && labels[col][i1].getCabinetId() != -1)
                                    && row != i1) {
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
                        System.out.println("onDragDone");
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            labels[col][row].setText("");
                            labels[col][row].setId("right");
                            labels[col][row].setValid(true);
                            labels[col][row].setTeacherId(-1);
                            labels[col][row].setSubjectId(-1);
                            labels[col][row].setCabinetId(-1, 0);

                            HashSet<Integer> ids = new HashSet();
                            for (int i = 1; i < cols; i++) {
                                if (labels[col][i].getTeacherId() != -1) {
                                    if (!ids.contains(labels[col][i].getTeacherId())) {
                                        ids.add(labels[col][i].getTeacherId());
                                        labels[col][i].setValid(true);
                                    } else {
                                        labels[col][i].setValid(false);
                                    }
                                } else {
                                    labels[col][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                                }
                            }

                            HashSet<Integer> idCab = new HashSet();
                            for (int i = 1; i < cols; i++) {
                                if (labels[col][i].isValid()) {
                                    if (labels[col][i].getCabinetId() != -1) {
                                        if (!idCab.contains(labels[col][i].getCabinetId())) {
                                            idCab.add(labels[col][i].getCabinetId());
                                            labels[col][i].setValid(true);
                                        } else {
                                            labels[col][i].setValid(false);
                                        }
                                    } else {
                                        labels[col][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                                    }
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
                        cabinetId = labels[col][row].getCabinetId();
                        db.setContent(content);
                        event.consume();
                    }
                });
            }
    }

    public void checkAll() {
        for (int j = 1; j < rows; j++) {
            HashSet<Integer> ids = new HashSet();
            for (int i = 1; i < cols; i++) {
                if (labels[j][i].getTeacherId() != -1) {
                    if (!ids.contains(labels[j][i].getTeacherId())) {
                        ids.add(labels[j][i].getTeacherId());
                        labels[j][i].setValid(true);
                    } else {
                        labels[j][i].setValid(false);
                    }
                } else {
                    labels[j][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                }
            }


            HashSet<Integer> idCab = new HashSet();
            for (int i = 1; i < cols; i++) {
                if (labels[j][i].isValid()) {
                    if (labels[j][i].getCabinetId() != -1) {
                        if (!idCab.contains(labels[j][i].getCabinetId())) {
                            idCab.add(labels[j][i].getCabinetId());
                            labels[j][i].setValid(true);
                        } else {
                            labels[j][i].setValid(false);
                        }
                    } else {
                        labels[j][i].setStyle("-fx-background-color:#ffffff;-fx-border-width:0.5;-fx-border-color:black");
                    }
                }
            }

            for (int i = 1; i < cols; i++) {
                if (!labels[j][i].isValid()) {
                    labels[j][i].setId("wrong");
                    labels[j][i].setStyle("-fx-background-color:#ff5c33;-fx-border-width:0.5;-fx-border-color:black");
                } else {
                    labels[j][i].setId("right");
                    labels[j][i].setStyle("-fx-background-color:#fff;-fx-border-width:0.5;-fx-border-color:black");
                }
            }
        }

    }

    public void saveClick (ActionEvent actionEvent) throws IOException {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (int i = 1; i < rows; i++)
            for (int j = 1; j < cols; j++) {
                if (labels[i][j].getSubjectId() != -1)
                    schedules.add(new Schedule(labels[i][j].getSubjectId(), j - 1,
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
        spreadsheet.setDefaultRowHeight((short)600);
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

        for (int i = 0; i < rows; i++) {
            row = spreadsheet.createRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(labels[i][j].getText());
                cell.setCellStyle(style);
            }
        }

        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Файл сохранен");
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.showAndWait();
    }
}
