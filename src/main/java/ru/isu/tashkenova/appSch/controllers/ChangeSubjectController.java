package ru.isu.tashkenova.appSch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeSubjectController {


	@FXML
	private Button save;

	@FXML
	private TextField name;

	@FXML
	private TextField shortName;

	public void setNewFormWow(String name, String shortName) {
		this.name.setText(name);
		this.shortName.setText(shortName);
	}


	public void initialize() {

	}

	@FXML
	void saveButtonClicked(ActionEvent event) {
		Stage stag = (Stage) save.getScene().getWindow();
		stag.close();
	}

}



