package com.kwitpiotr.views;

import com.kwitpiotr.model.Client;
import com.kwitpiotr.util.DateFormatUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientDetailsController {
	@FXML
	private Label nameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label birthDateLabel;
	@FXML
	private Label peselLabel;
	@FXML
	private Button deleteButton;
	
	public ClientDetailsController(){}
	
	public void showClientDetails(Client client){
		if(client != null){
			nameLabel.setText(client.getName());
			lastNameLabel.setText(client.getLastName());
			birthDateLabel.setText(DateFormatUtil.format(client.getBirthDate()));
			peselLabel.setText(Long.toString(client.getPesel()));
		}else{
			nameLabel.setText("");
			lastNameLabel.setText("");
			birthDateLabel.setText("");
			peselLabel.setText("");
		}
	}

}
