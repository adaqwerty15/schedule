package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ListEditor {
    void tableClicked(MouseEvent mouseEvent);
    void saveButtonClicked(ActionEvent actionEvent);
    void deleteButtonClicked(ActionEvent actionEvent);
    void selectButtonClicked(ActionEvent actionEvent);
    void initialize();
}
