package ru.isu.tashkenova.appSch.controllers;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class AdminPanelController extends ZavuchPanelController{

    @FXML
    public void buttonUsersClick(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/users.fxml"));
        stage.setTitle("Users");
        stage.setScene(new Scene(root, 1031, 791));
        stage.show();
    }
}
