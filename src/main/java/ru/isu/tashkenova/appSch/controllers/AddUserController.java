package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.Role;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserView;

import java.io.IOException;
import java.util.ArrayList;

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
    private ChoiceBox troleId;

    @FXML
    private TextField tlogin;

    @FXML
    private PasswordField tpassword;

    @FXML
    private Label passwordEmpty;

    @FXML
    private Label loginEmpty;

    @FXML
    private Label namaEmpty;

    @FXML
    private Label surnameEmpty;

    @FXML
    private Label roleEmpty;

    User user = new User();
    ArrayList<String> s = new ArrayList<>();

    public void setChoiseBox (ObservableList<Role> l) {

        for (Role role:l) {
            s.add(role.name);
        }

        troleId.setItems(FXCollections.observableArrayList(s));

    }

    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        boolean check_surname = DataValidation.textFieldIsSurnameEmpty(tsurname, surnameEmpty, "Заполните поле 'Фамилия'. Цифры недопустимы.");
        boolean check_name = DataValidation.textFieldIsNameEmpty(tname, namaEmpty, "Заполните поле 'Имя'. Цифры недопустимы.");
        boolean check_role = DataValidation.textFieldIsRoleEmpty(troleId, roleEmpty, "Выберите статус.");
        boolean check_login = DataValidation.textFieldIsloginEmpty(tlogin, loginEmpty, "Заполните поле 'Логин'.");
        boolean check_password = DataValidation.textFieldIsPasswordEmpty(tpassword, passwordEmpty, "Заполните поле 'Пароль'.");

        if (check_surname && check_name && check_login && check_password && check_role) {
            String user_name = tname.getText();
            String user_surname = tsurname.getText();
            String user_fathername = tfathername.getText();
            int user_roleId = 2;
            String user_rolename = String.valueOf(troleId.getValue());
            try {
                user_roleId = s.indexOf(troleId.getValue());
            } catch (Exception e) {
                user_roleId = 2;
            }

            String user_login = tlogin.getText();
            String user_password = tpassword.getText();

            user = new User(0, user_roleId, user_name, user_surname,
                    user_fathername, user_login, user_password, 1);


            RetrofitService.RetrofitBuildU().addUser(user).execute();
            UsersController.data.add(new UserView(user.getName(), user.getSurname(),
                    user.getFathername(), user.getLogin(), user.getRoleId(), user_rolename, user.getId(), user.getPassword()));

            name.getScene().getWindow().hide();

        }

    }





}
