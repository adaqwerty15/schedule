package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.isu.tashkenova.appSch.Cabinet;
import ru.isu.tashkenova.appSch.CabinetView;
import ru.isu.tashkenova.appSch.RetrofitService;

import java.io.IOException;


public class AddClassroomController {

    @FXML
    private TextField name;

    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        String tname = name.getText();
        Cabinet cabinet = new Cabinet(0, tname);
        RetrofitService.RetrofitBuildU().addCabinet(cabinet).execute();
        ClassroomController.data.add(new CabinetView(cabinet.getId(), cabinet.getName()));
        name.getScene().getWindow().hide();
    }
}
