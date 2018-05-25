package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import retrofit2.Call;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;

import java.util.ArrayList;
import java.util.List;

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
        ObservableList content;

        Call<List<User>> users = RetrofitService.RetrofitBuild().getUsers();
        content = FXCollections.observableArrayList(
                users.body();
        );

        //usersTable.setItems(users);
    }

    public void resetPasswordClicked(ActionEvent actionEvent) {
    }
}
