package ru.isu.tashkenova.appSch.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.User;
import ru.isu.tashkenova.appSch.UserService;
import ru.isu.tashkenova.appSch.UserView;

import java.io.IOException;
import java.util.List;

public class UsersController implements ListEditor{

    public static ObservableList<UserView> data = FXCollections.observableArrayList();
    UserService service;


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

    @FXML
    private Label name;



    ObservableList content;


    @Override
    public void tableClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void saveButtonClicked(ActionEvent actionEvent) {

    }


    @Override
    public void initialize() throws IOException {

        columnName.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
        columnSurname.setCellValueFactory(celldata -> celldata.getValue().lastnameProperty());
        columnRoleId.setCellValueFactory(celldata -> celldata.getValue().roleIdProperty().asObject());
        columnfatherrname.setCellValueFactory(celldata -> celldata.getValue().fathernameProperty());
        columnlogin.setCellValueFactory(celldata -> celldata.getValue().loginProperty());

		        ContextMenu cm = new ContextMenu();
        
        MenuItem menuItemPut = new MenuItem("Put");
        MenuItem menuItemDelete = new MenuItem("Delete");
        MenuItem menuItemAdd = new MenuItem("Add");
        
        menuItemPut.setOnAction((ActionEvent event) -> {
            try {
                UserView user = usersTable.getSelectionModel().getSelectedItem();
                Stage stage_add = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/change_user.fxml"));
                Parent root_add;
                root_add = fxmlLoader.load();              
                ChangeUserController c = fxmlLoader.getController();
                c.setUserv(user, usersTable.getSelectionModel().getSelectedIndex());
                stage_add.setTitle("Change user");
                stage_add.setScene(new Scene(root_add, 456, 439));
                stage_add.show();     
                usersTable.refresh();
            } catch (IOException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        menuItemDelete.setOnAction((ActionEvent event) -> {
            try {
                UserView user = usersTable.getSelectionModel().getSelectedItem();
                service.deleteUser(user.getId()).execute();
                data.remove(usersTable.getSelectionModel().getSelectedIndex());
                usersTable.refresh();
            } catch (IOException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        menuItemAdd.setOnAction((ActionEvent event) -> {
            try {
                Stage stage_add = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root_add = fxmlLoader.load(getClass().getClassLoader().getResource("views/add_user.fxml"));
                stage_add.setTitle("Добавить пользователя");
                stage_add.setScene(new Scene(root_add, 456, 439));
                stage_add.show();
                usersTable.refresh();
            } catch (IOException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        cm.getItems().add(menuItemPut);
        cm.getItems().add(menuItemDelete);
        cm.getItems().add(menuItemAdd);
		
		
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
                    user.getFathername(), user.getLogin(), user.getRoleId(), user.getId(), user.getPassword()));
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

    @Override
    public void addButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage_add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root_add = fxmlLoader.load(getClass().getClassLoader().getResource("views/add_user.fxml"));
        stage_add.setTitle("Добавить пользователя");
        stage_add.setScene(new Scene(root_add, 456, 439));
        stage_add.show();
        usersTable.refresh();

    }

    public void changeButtonClicked(ActionEvent actionEvent) throws IOException {
        UserView user = usersTable.getSelectionModel().getSelectedItem();
        Stage stage_add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/change_user.fxml"));
        Parent root_add = fxmlLoader.load();
        ChangeUserController c = fxmlLoader.getController();
        c.setUserv(user, usersTable.getSelectionModel().getSelectedIndex());
        stage_add.setTitle("Изменить пользователя");
        stage_add.setScene(new Scene(root_add, 456, 439));
        stage_add.show();

        usersTable.refresh();
    }


    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }


}


