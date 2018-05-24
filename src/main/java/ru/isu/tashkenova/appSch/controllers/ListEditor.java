package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface ListEditor {
    void tableClicked(MouseEvent mouseEvent);
    void saveButtonClicked(ActionEvent actionEvent);
    void deleteButtonClicked(ActionEvent actionEvent) throws IOException;
    void selectButtonClicked(ActionEvent actionEvent);
    void initialize() throws IOException;
}
