package com.kwitpiotr.views;

import com.kwitpiotr.model.Client;
import com.kwitpiotr.util.DateFormatUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClientEditDialogController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField birthDateField;
	@FXML
	private TextField peselField;

	private Stage dialogStage;
    private Client client;
    private boolean confirmed = false;
    
    @FXML
    private void initialize(){}
    
    public void setDialogStage(Stage dialogStage){
    	this.dialogStage = dialogStage;
    }
    
    public void setClient(Client client){
    	this.client = client;
    	
    	if(client.getName() != null){
        	nameField.setText(client.getName());
        	lastNameField.setText(client.getLastName());
        	birthDateField.setText(DateFormatUtil.format(client.getBirthDate()));
        	birthDateField.setPromptText("dd-MM-yyyy");
        	peselField.setText(Long.toString(client.getPesel()));
    	}else{
        	nameField.setText("");
        	lastNameField.setText("");
        	birthDateField.setText("");
        	peselField.setText("");
    	}

    }
    
    @FXML
    private void handleOk(){
    	if(isInputValid()){
    		client.setName(nameField.getText());
    		client.setLastName(lastNameField.getText());
    		client.setBirthDate(DateFormatUtil.parse(birthDateField.getText()));
    		client.setPesel(Long.parseLong(peselField.getText()));
    		
    		confirmed = true;
    		dialogStage.close();
    	}
    }
    
    @FXML
    private void handleCancel(){
    	dialogStage.close();
    }
    
    private boolean isInputValid(){
    	String errorMessage = "";
    	
    	if(nameField.getText() == null || nameField.getText().length() == 0){
    		errorMessage += "Pole 'imię' nie zostało wypełnione poprawnie.\n";
    	}else if(!nameField.getText().matches("[a-zA-Z]+")){
    		errorMessage += "Pole 'imię' może zawierać wyłącznie litery.";
    	}
    	if(lastNameField.getText() == null || lastNameField.getText().length() == 0){
    		errorMessage += "Pole 'nazwisko' nie zostało wypełnione poprawnie.\n";
    	}else if(!lastNameField.getText().matches("[a-zA-Z]+")){
    		errorMessage += "Pole 'nazwisko' może zawierać wyłącznie litery.";
    	}
    	if(birthDateField.getText() == null || birthDateField.getText().length() == 0){
    		errorMessage += "Pole 'data urodzenia' nie zostało wypełnione poprawnie.\n";
    	}else{
    		if(!DateFormatUtil.validDate(birthDateField.getText())){
    			errorMessage += "Wypełnij pole 'data urodzenia' urzywając formatu dd-MM-yyyy.";
    		}
    	}
    	if(peselField.getText() == null || peselField.getText().length() == 0){
    		errorMessage += "Pole 'pesel' nie zostało wypełnione poprawnie.";
    	}else if(peselField.getText().length() != 11){
    		errorMessage += "Ilość znaków w podanym PESEL'u jest niepoprawna.";
    	}else{
    		//try to parse PESEL into Integer
    		try{
    			Long.parseLong(peselField.getText());
    		}catch(NumberFormatException e){
    			errorMessage += "PESEL musi składać się wyłącznie z cyfr.\n";
    		}
    	}
    	
    	if(errorMessage.length() == 0){
    		return true;
    	}else{
    		//Show the error massega
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.initOwner(dialogStage);
    		alert.setTitle("Nieprawidłowe pole");
    		alert.setHeaderText("Popraw pola");
    		alert.setContentText(errorMessage);
    		
    		alert.showAndWait();
    		
    		return false;
    	}
    	
    }
    
    public boolean isConfirmed(){
    	return confirmed;
    }
}
