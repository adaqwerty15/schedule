package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.isu.tashkenova.appSch.Cabinet;
import ru.isu.tashkenova.appSch.CabinetView;
import ru.isu.tashkenova.appSch.RetrofitService;

import java.io.IOException;

public class ChangeCabinetController {
    @FXML
    private TextField name;

    int id;
    int stId;

    public void setCabinet(CabinetView cabinet, int st_id) {
        name.setText(cabinet.getName());
        id = cabinet.getId();
        stId = st_id;
    }

    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        String tname = name.getText();
        Cabinet cabinet = new Cabinet(id, tname);
        RetrofitService.RetrofitBuildU().putCabinet(id,cabinet).execute();
        ClassroomController.data.set(stId,new CabinetView(cabinet.getId(), cabinet.getName()));
        name.getScene().getWindow().hide();
    }
}
