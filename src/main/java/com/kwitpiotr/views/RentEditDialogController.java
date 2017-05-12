package com.kwitpiotr.views;

import java.time.LocalDate;

import com.kwitpiotr.model.Rent;
import com.kwitpiotr.util.DateFormatUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RentEditDialogController {
	
	@FXML
	private Label clientNameLabel;
	@FXML
	private Label clientLastNameLabel;
	@FXML
	private Label movieLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;
	
	private Stage dialogStage;
	private Rent rent;
	private LocalDate tempEndDate;
    private boolean confirmed = false;
    
    @FXML
    private void initialize(){}
    
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setRent(Rent rent){
		this.rent = rent;
		
		if(rent.getMovie() != null){
			clientNameLabel.setText(rent.getClient().getName());
			clientLastNameLabel.setText(rent.getClient().getLastName());
			movieLabel.setText(rent.getMovie().getTitle());
			startDateLabel.setText(DateFormatUtil.format(rent.getStartDate()));
			endDateLabel.setText(rent.getEndDate() != null ? DateFormatUtil.format(rent.getEndDate()) : "");
		}else{
			clientNameLabel.setText("");
			clientLastNameLabel.setText("");
			movieLabel.setText("");
			startDateLabel.setText("");
			endDateLabel.setText("");
		}
	}
	
	public void handleReturnMovie(){
		if(!rent.isReturned()){
			tempEndDate = LocalDate.now();
			endDateLabel.setText(DateFormatUtil.format(tempEndDate));
		}else{
			//Show the error massage
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.initOwner(dialogStage);
    		alert.setTitle("Nieprawidłowe pole");
    		alert.setHeaderText("Popraw pola");
    		alert.setContentText("Nie można zwrócić filmu, gdyż to wypożeczenie zostało już wcześniej zakończone.");
    		
    		alert.showAndWait();
		}
	}
	
	public void handleOk(){
		if(!rent.isReturned() && tempEndDate != null){
			rent.setEndDate(tempEndDate);
			rent.returnItem();
		}
		dialogStage.close();
	}
	
	public void handleCancel(){
		dialogStage.close();
	}
	
	
    public boolean isConfirmed(){
    	return confirmed;
    }

}
