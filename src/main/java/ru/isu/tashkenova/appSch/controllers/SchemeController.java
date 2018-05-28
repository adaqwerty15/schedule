package ru.isu.tashkenova.appSch.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class SchemeController {

    @FXML
    private GridPane gridpane = new GridPane();

    @FXML
    private TableView scheme;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    public void initialize() {
        l1.setText("Литература \nБурзунова Г. Е. \n206");
        l2.setText("Suda");
        gridpane.setPadding(new Insets(40));
        ArrayList<String> a = new ArrayList<String>(Arrays.asList("","5и","5м", "6л", "6м", "7а", "7б", "8а", "8б", "8и", "8л", "9а", "9е", "9и", "9л", "9м",
                "9э", "10а", "10е", "10и", "10л", "10м", "11е", "11и", "11л", "11м", "11э"));
        ArrayList<String> time = new ArrayList<String>(Arrays.asList("8.00-8.40", "8.50-9.30", "9.40-10.20", "10.40-11.20", "11.40-12.20", "12.30-13.10", "13.20-14.00",
                "14.10-14.50","15.00-15.40","16.00-16.40", "17.00-17.40", "17.50-18.30", "18.40-19.20"));
        gridpane.setGridLinesVisible(true);
        gridpane.setPrefWidth(100*26);


        Label[][] labels = new Label[time.size()+1][a.size()];


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(100);

        gridpane.add(new Text(a.get(0)), 0, 0);
        for(int i = 1; i<a.size(); i++) {
            labels[0][i] = new Label(a.get(i));
            gridpane.getColumnConstraints().add(columnConstraints);
            gridpane.add(labels[0][i], i, 0);
        }
        gridpane.getColumnConstraints().add(columnConstraints);

        for (int j=1; j<time.size(); j++) {
            labels[j][0] = new Label("\n"+time.get(j)+"\n");
            gridpane.add(labels[j][0],0,j);
        }

        for(int i = 1; i<time.size(); i++)
            for (int j=1; j<a.size(); j++) {
                labels[i][j] = new Label("\n\n\n");
                gridpane.add( new Label("eddee"),j,i);
            }

        l1.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = l1.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(l1.getText());
                db.setContent(content);

                event.consume();
            }
        });

        l2.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != l2 &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        labels[1][1].setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != l2 &&
                        event.getDragboard().hasString()) {
                    //l2.setFill(Color.GREEN);
                }

                event.consume();
            }
        });

        l2.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                //labels[1][1].setFill(Color.BLACK);

                event.consume();
            }
        });

        l2.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    l2.setText(db.getString());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        l1.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    l1.setText("");
                }

                event.consume();
            }
        });







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
