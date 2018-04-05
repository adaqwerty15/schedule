package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class UsersController implements ListEditor{

    @FXML
    private TableView usersTable;

    @Override
    public void tableClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void saveButtonClicked(ActionEvent actionEvent) {

    }

    @Override
    public void deleteButtonClicked(ActionEvent actionEvent) {

    }

    @Override
    public void selectButtonClicked(ActionEvent actionEvent) {

    }

    @Override
    public void initialize() {

    }

    public void resetPasswordClicked(ActionEvent actionEvent) {
    }
}
