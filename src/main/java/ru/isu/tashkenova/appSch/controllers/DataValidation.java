package ru.isu.tashkenova.appSch.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataValidation {

    public static boolean textFieldIsSurnameEmpty(TextField tsurname, Label surnameEmpty, String s) {
        boolean check = true;
        String message = null;
        Color c = Color.GREEN;
        DropShadow ds = new DropShadow();
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я]+");
        Matcher matcher = pattern.matcher(tsurname.getText());

        if(tsurname.getText().isEmpty() || !(matcher.matches())){
            c = Color.RED;
            check = false;
            message = s;
        }
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(c);
        tsurname.setEffect(ds);
        surnameEmpty.setText(message);
        return check;
    }

    public static boolean textFieldIsloginEmpty(TextField tlogin, Label loginEmpty, String s) {
        boolean check = true;
        String message = null;
        Color c = Color.GREEN;
        DropShadow ds = new DropShadow();

        if(tlogin.getText().isEmpty() ){
            c = Color.RED;
            check = false;
            message = s;
        }
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(c);
        tlogin.setEffect(ds);
        loginEmpty.setText(message);
        return check;
    }

    public static boolean textFieldIsPasswordEmpty(PasswordField tpassword, Label passwordEmpty, String s) {
        boolean check = true;
        String message = null;
        Color c = Color.GREEN;
        DropShadow ds = new DropShadow();

        if(tpassword.getText().isEmpty() ){
            c = Color.RED;
            check = false;
            message = s;
        }
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(c);
        tpassword.setEffect(ds);
        passwordEmpty.setText(message);
        return check;
    }

    public static boolean textFieldIsNameEmpty(TextField tname, Label namaEmpty, String s) {
        boolean check = true;
        String message = null;
        Color c = Color.GREEN;
        DropShadow ds = new DropShadow();
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я]+");
        Matcher matcher = pattern.matcher(tname.getText());

        if(tname.getText().isEmpty() || !(matcher.matches())){
            c = Color.RED;
            check = false;
            message = s;
        }
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(c);
        tname.setEffect(ds);
        namaEmpty.setText(message);
        return check;
    }

    public static boolean textFieldIsRoleEmpty(ChoiceBox troleId, Label roleEmpty, String s) {
        String r = (String) troleId.getValue();
        boolean check = true;
        String message = null;
        Color c = Color.GREEN;
        DropShadow ds = new DropShadow();
        if(r==null){
            c = Color.RED;
            check = false;
            message = s;
        }
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(c);
        troleId.setEffect(ds);
        roleEmpty.setText(message);
        return check;
    }
}
