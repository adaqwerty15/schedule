package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserView;

import java.io.IOException;

public class AddUserController {

    @FXML
    private Label name;

    @FXML
    private TextField tname;

    @FXML
    private TextField tsurname;

    @FXML
    private TextField tfathername;

    @FXML
    private TextField troleId;

    @FXML
    private TextField tlogin;

    @FXML
    private PasswordField tpassword;

    User user = new User();

    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        String user_name = tname.getText();
        String user_surname = tsurname.getText();
        String user_fathername = tfathername.getText();
        int user_roleId = -1;
        try {
             user_roleId = Integer.parseInt(troleId.getText());
        }

        catch (Exception e) {
             user_roleId = -1;
        }

        String user_login = tlogin.getText();
        String user_password = tpassword.getText();

        user = new User(0,user_roleId,user_name,user_surname,
                user_fathername, user_login, user_password, 1);


        RetrofitService.RetrofitBuild().addUser(user).execute();
        UsersController.data.add(new UserView(user.getName(), user.getSurname(),
                user.getFathername(), user.getLogin(), user.getRoleId(), user.getId(), user.getPassword()));

        name.getScene().getWindow().hide();

    }





}
