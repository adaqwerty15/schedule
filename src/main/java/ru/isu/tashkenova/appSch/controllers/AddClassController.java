package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by adaqwerty15 on 01.04.2018.
 */

public class AddClassController {

    @FXML
    private Button exitButton;

    public void saveButtonClicked(ActionEvent actionEvent) {
        exitButton.getScene().getWindow().hide();
    }
}
