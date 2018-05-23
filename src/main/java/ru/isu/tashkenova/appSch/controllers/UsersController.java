package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import retrofit2.Call;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersController implements ListEditor{

    @FXML
    private TableView usersTable;

	//private ObservableList content;

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
        Response<List<User>> users = RetrofitService.RetrofitBuild().getUsers().execute();
		//content = FXCollections.observableArrayList(
		//		users.body()
		//);

		//System.out.print(content.toString());

		//usersTable.setItems(users);
    }

    public void resetPasswordClicked(ActionEvent actionEvent) {
    }
}
