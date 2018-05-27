package ru.isu.tashkenova.appSch.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Arrays;

public class SchemeController {
    @FXML
    private TableView scheme;

    public void initialize() {

        ArrayList<String> a = new ArrayList<String>(Arrays.asList("5и","5м", "6л", "6м", "7а", "7б", "8а", "8б", "8и", "8л", "9а", "9е", "9и", "9л", "9м",
                "9э", "10а", "10е", "10и", "10л", "10м", "11е", "11и", "11л", "11м", "11э"));
        TableColumn[] rows = new TableColumn[a.size()];
        TableCell t = new TableCell();

         for(int i = 0; i<a.size(); i++) {
             rows[i] = new TableColumn(a.get(i));
             //rows[i].setMinWidth(200);
             scheme.getColumns().addAll(rows[i]);

         }
    }


}
