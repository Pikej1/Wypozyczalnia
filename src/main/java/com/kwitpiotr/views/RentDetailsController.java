package com.kwitpiotr.views;

import com.kwitpiotr.model.Rent;
import com.kwitpiotr.util.DateFormatUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentDetailsController {

	@FXML
	private Label clientNameLabel;
	@FXML
	private Label clientLastNameLabel;
	@FXML
	private Label movieTitleLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;
	@FXML
	private Label statusLabel;

	public RentDetailsController(){}
	
	public void showRentDetails(Rent rent){
		if(rent != null){
			clientNameLabel.setText(rent.getClient().getName());
			clientLastNameLabel.setText(rent.getClient().getLastName());
			movieTitleLabel.setText(rent.getMovie().getTitle());
			startDateLabel.setText(DateFormatUtil.format(rent.getStartDate()));
			if(rent.getEndDate() != null){
				endDateLabel.setText(DateFormatUtil.format(rent.getEndDate()));
			}else{
				endDateLabel.setText("");
			}
			if(rent.isReturned()){
				statusLabel.setText("Zwrócony");
			}else{
				statusLabel.setText("W trakcie wypożecznia");
			}
		}else{
			clientNameLabel.setText("");
			clientLastNameLabel.setText("");
			movieTitleLabel.setText("");
			startDateLabel.setText("");
			endDateLabel.setText("");
			statusLabel.setText("");
		}
	}
}
