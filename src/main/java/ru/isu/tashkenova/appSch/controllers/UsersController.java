package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserService;
import ru.isu.tashkenova.appSch.UserView;

import java.io.IOException;
import java.util.List;

public class UsersController implements ListEditor{

    private ObservableList<UserView> data = FXCollections.observableArrayList();
    private UserService service;


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

    @FXML
    private TableColumn<UserView, String> columnlogin;




    private ObservableList content;


    @Override
    public void tableClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void saveButtonClicked(ActionEvent actionEvent) {

    }


    @Override
    public void selectButtonClicked(ActionEvent actionEvent) {

    }

    @Override
    public void initialize() throws IOException {

        usersTable.setEditable(true);

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Menu 1");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Menu 2");
        cm.getItems().add(mi2);

        usersTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(usersTable, t.getScreenX(), t.getScreenY());
                }
            }
        });



        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
        columnSurname.setCellValueFactory(celldata -> celldata.getValue().lastnameProperty());
        columnRoleId.setCellValueFactory(celldata -> celldata.getValue().roleIdProperty().asObject());
        columnfatherrname.setCellValueFactory(celldata -> celldata.getValue().fathernameProperty());
        columnlogin.setCellValueFactory(celldata -> celldata.getValue().loginProperty());

        usersTable.getSelectionModel().setCellSelectionEnabled(true);

        Gson gson = new GsonBuilder()
                .setDateFormat("MMM dd, yyyy")
                .create();

        service = RetrofitService.RetrofitBuild();

        Response<List<User>> users = service.getUsers().execute();
        content = FXCollections.observableArrayList(
                users.body()
        );
        //System.out.print(content.toString());

        for (User w: users.body()) {
            User user = gson.fromJson(gson.toJson(w), User.class);
            data.add(new UserView(user.getName(), user.getSurname(),
                    user.getFathername(), user.getLogin(), user.getRoleId(), user.getId()));
        }
        usersTable.setItems(data);




        usersTable.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                UserView user = usersTable.getSelectionModel().getSelectedItem();
            }
        });
    }


    @FXML
    @Override
    public void deleteButtonClicked(ActionEvent event) throws IOException {
        UserView user = usersTable.getSelectionModel().getSelectedItem();
        service.deleteUser(user.getId()).execute();
        data.remove(usersTable.getSelectionModel().getSelectedIndex());
        usersTable.refresh();
    }


    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }

    public void resetPasswordClicked(ActionEvent actionEvent) {
    }

}
