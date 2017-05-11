package com.kwitpiotr.views;

import com.kwitpiotr.model.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MovieEditDialogController {

	@FXML
	private TextField titleField;
	@FXML
	private TextField manufYearField;
	@FXML
	private TextField genreField;
	
	private Stage dialogStage;
	private Movie movie;
    private boolean confirmed = false;
    
	@FXML
	private void initialize(){}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setMovie(Movie movie){
		this.movie = movie;
		
		if(movie.getTitle() != null){
			titleField.setText(movie.getTitle());
			manufYearField.setText(Integer.toString(movie.getManufactureYear()));
			genreField.setText(movie.getGenre());
		}else{
			titleField.setText("");
			manufYearField.setText("");
			genreField.setText("");
		}
	}
	
	@FXML
	void handleOk(){
		if(isInputValid()){
			movie.setTitle(titleField.getText());
			movie.setManufactureYear(Integer.parseInt(manufYearField.getText()));
			movie.setGenre(genreField.getText());
			movie.setRented(false);
			
			confirmed = true;
			dialogStage.close();
		}
	}
	
	@FXML
	void handleCancel(){
		dialogStage.close();
	}
	
	private boolean isInputValid(){
    	String errorMessage = "";
    	
    	if(titleField.getText() == null || titleField.getText().length() == 0){
    		errorMessage += "Pole 'tytuł' nie zostało wypełnione poprawnie.\n";
    	}
    	if(genreField.getText() == null || titleField.getText().length() == 0){
    		errorMessage += "Pole 'gatunek' nie zostało wypełnione poprawnie.\n";
    	}
    	if(manufYearField.getText() == null || manufYearField.getText().length() == 0){
    		errorMessage += "Podano nieprawidłowy rok produkcji";
    	}else{
    		//try to parse manufactureYear into Integer
    		try{
    			Integer.parseInt(manufYearField.getText());
    		}catch(NumberFormatException e){
    			errorMessage += "Rok produkcji musi składać się wyłącznie z cyfr.\n";
    		}
    	}
    	
    	if(errorMessage.length() == 0){
    		return true;
    	}else{
    		//Show the error massage
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
