package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserView;

import java.io.IOException;
import java.util.List;

public class UsersController implements ListEditor{

    @FXML
    private TableView<UserView> usersTable = new TableView<>();

    @FXML
    private TableColumn<UserView, String> columnName;

    @FXML
    private TableColumn<UserView, String> columnSurname;

    @FXML
    private TableColumn<UserView, String> columnfatherrname;

    @FXML
    private TableColumn<UserView, Integer> columnRoleId;


    ObservableList content;


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
    public void initialize() throws IOException {

        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
        //columnSurname.setCellValueFactory(celldata -> celldata.getValue().lastnameProperty());

        Response<List<User>> users = RetrofitService.RetrofitBuild().getUsers().execute();
        content = FXCollections.observableArrayList(
                users.body()
        );
        System.out.print(content.toString());
        usersTable.setItems(content);
    }

    public void resetPasswordClicked(ActionEvent actionEvent) {
    }
}
