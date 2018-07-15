package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.Subject;
import ru.isu.tashkenova.appSch.SubjectView;

import java.io.IOException;

public class AddSubjectController {
    @FXML
    private Button save;

    @FXML
    private TextField name;

    @FXML
    private TextField shortName;

    @FXML
    private ComboBox group;

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException {
        String tname = name.getText();
        String tshortName = shortName.getText();
        int tgroup;
        try {
             tgroup = Integer.parseInt(String.valueOf(group.getValue()));
        }
        catch (Exception e) {
             tgroup = 1;
        }

        Subject subject = new Subject(0, tgroup, tname, tshortName, 0);

        RetrofitService.RetrofitBuildU().addSubject(subject).execute();
        SubjectsController.data.add(new SubjectView(0, tgroup, tname, tshortName));
        name.getScene().getWindow().hide();

    }

}
