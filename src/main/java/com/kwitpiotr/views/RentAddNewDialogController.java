package com.kwitpiotr.views;

import java.time.LocalDate;

import com.kwitpiotr.MainApp;
import com.kwitpiotr.model.Client;
import com.kwitpiotr.model.Movie;
import com.kwitpiotr.model.Rent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RentAddNewDialogController {
	
	@FXML
	private TableView<Client> clientTable;
	@FXML
	private TableColumn<Client, String> nameColumn;
	@FXML
	private TableColumn<Client, String> lastNameColumn;
	
	@FXML
	private TableView<Movie> movieTable;
	@FXML
	private TableColumn<Movie, String> titleColumn;
	@FXML
	private TableColumn<Movie, String> genreColumn;
	
	private Stage dialogStage;
	private Rent rent;
	private Client client;
	private Movie movie;
    private boolean confirmed = false;
	
	public RentAddNewDialogController(){}
	
	@FXML
	private void initialize(){
		
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
		
		clientTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setClient(newValue));
		
		movieTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setMovie(newValue));
	}
	
	public void setDialogStage(Stage dialogStage){
    	this.dialogStage = dialogStage;
    }
	
	private void setClient(Client client){
		this.client = client;
	}
	
	private void setMovie(Movie movie){
		this.movie = movie;
	}
	
	public void setRent(Rent rent){
		this.rent = rent;
	}
	
	@FXML
	public void handleOk(){
		if(isInputValid()){
			rent.setClient(client);
			rent.setMovie(movie);
			rent.setStartDate(LocalDate.now());
			rent.setEndDate(null);
			movie.setRented(true);
			
			confirmed = true;
			dialogStage.close();
		}
	}
	
	@FXML
	void handleCancel(){
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(client == null){
			errorMessage += "Nie wybrano klienta.";
		}
		if(movie == null){
			errorMessage += "Nie wybrano filmu.";
		}
		
		if(errorMessage.length() == 0){
    		return true;
    	}else{
    		//Show the error message
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.initOwner(dialogStage);
    		alert.setTitle("Nieprawidłowe pole");
    		alert.setHeaderText("Wybierz pola");
    		alert.setContentText(errorMessage);
    		
    		alert.showAndWait();
    		
    		return false;
    	}
	}
	
	public boolean isConfirmed(){
    	return confirmed;
    }
	
	public void setRepository(MainApp mainApp){
		clientTable.setItems(mainApp.getClientRep());
		//movieTable.setItems(mainApp.getMovieRep());
		movieTable.setItems(mainApp.getAvaiableMovies());
	}
}
