package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;

	@FXML
	public ChoiceBox<String> choiceBox;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertButton;

	private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
	private static final String F_TO_C_TEXT = "Fahrenheit to Celsius";
	private boolean isC_TO_F = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_TO_F_TEXT);
		choiceBox.getItems().add(F_TO_C_TEXT);

		choiceBox.setValue(C_TO_F_TEXT);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.equals(C_TO_F_TEXT)){     //If user has selected Celsius to Fahrenheit
					isC_TO_F = true;
				}
				else{                                 //If user has selected Fahrenheit to Celsius
					isC_TO_F = false;
				}
		});

		convertButton.setOnAction(event -> {
			convert();
		});
	}

	private void convert() {
		String input = userInputField.getText();

		float enteredTemperature = Float.parseFloat(input);

		float newTemperature = 0.0f;
		if(isC_TO_F){
			newTemperature = (enteredTemperature * 9 / 5) + 32;
		}
		else {
			newTemperature = (enteredTemperature - 32) * 5 / 9;
		}
		display(newTemperature);
	}

	private void display(float newTemperature) {
		String unit = isC_TO_F? " F" : " C";
		System.out.println("The New Temperature is : " + newTemperature + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The New Temperature is : " + newTemperature + unit);
		alert.show();
	}
}
