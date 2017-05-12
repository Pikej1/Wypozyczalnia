package com.kwitpiotr.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import com.kwitpiotr.MainApp;
import com.kwitpiotr.model.Client;
import com.kwitpiotr.model.Movie;
import com.kwitpiotr.model.Rent;

import java.time.LocalDate;

public class RootController {
	
	@FXML
	private TableView<Movie> movieTable;
	@FXML
	private TableColumn<Movie, String> titleColumn;
	@FXML
	private TableColumn<Movie, String> genreColumn;
	
	@FXML
	private TableView<Client> clientTable;
	@FXML
	private TableColumn<Client, String> nameColumn;
	@FXML
	private TableColumn<Client, String> lastNameColumn;
	
	private MainApp mainApp;
	
	@FXML
	private TableView<Rent> rentTable;
	@FXML
	private TableColumn<Rent, String> rentingClientNameColumn;
	@FXML
	private TableColumn<Rent, String> rentingClientLastNameColumn;
	@FXML
	private TableColumn<Rent, String> rentedMovieColumn;
	@FXML
	private TableColumn<Rent, LocalDate> startDateColumn;
	
	@FXML
	private Button button;
	@FXML
	private Button secondButton;

	private int selectedTab;
	
	/**
	 * Contructor
	 */
	public RootController(){}
	
	@FXML
	private void initialize(){
		selectedTab = 1;
		
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
		
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		rentingClientNameColumn.setCellValueFactory(cellData -> cellData.getValue().clientProperty().
				get().nameProperty());
		rentingClientLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().clientProperty().
				get().lastNameProperty());
		rentedMovieColumn.setCellValueFactory(cellData -> cellData.getValue().movieProperty().get().titleProperty());
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
		
		showMovieDetails(null);
		showClientDetails(null);
		showRentDetails(null);
		
		movieTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMovieDetails(newValue));
		clientTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showClientDetails(newValue));
		rentTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showRentDetails(newValue));
		
		button.setOnAction(event -> movieTable.setItems(mainApp.getAvaiableMovies()));
		secondButton.setOnAction(event -> movieTable.setItems(mainApp.getMovieRep()));
	}
	/**
	 * Adding new Client after pressing button 'Dodaj nowy'
	 */
	@FXML
	private void handleNewClient(){
		Client newClient = new Client();
		boolean okClicked = mainApp.showClientEditDialog(newClient);
		if(okClicked){
			mainApp.getClientRep().add(newClient);
		}
	}
	
	@FXML
	private void handleEditCliet(){
		Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
		if(selectedClient != null){
			boolean okClicked = mainApp.showClientEditDialog(selectedClient);
			if(okClicked){
				showClientDetails(selectedClient);
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nie wybrano żadnej pozycji z listy");
			alert.setHeaderText("Nie wybrano klienta");
			alert.setContentText("Wybierz klienta z tabeli 'Klienci'.");
			
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNewMovie(){
		Movie newMovie = new Movie();
		boolean okClicked = mainApp.showMovieEditDialog(newMovie);
		if(okClicked){
			mainApp.getMovieRep().add(newMovie);
		}
	}
	
	@FXML
	private void handleEditMovie(){
		Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
		if(selectedMovie != null){
			boolean okClicked = mainApp.showMovieEditDialog(selectedMovie);
			if(okClicked){
				showMovieDetails(selectedMovie);
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nie wybrano żadnej pozycji z listy");
			alert.setHeaderText("Nie wybrano filmu");
			alert.setContentText("Wybierz film z tabeli 'Filmy'.");
			
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleEditRent(){
		Rent selectedRent = rentTable.getSelectionModel().getSelectedItem();
		if(selectedRent != null){
			boolean okClicked = mainApp.showRentEditDialog(selectedRent);
			if(okClicked){
				showRentDetails(selectedRent);
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nie wybrano żadnej pozycji z listy");
			alert.setHeaderText("Nie wybrano wypożyczenia");
			alert.setContentText("Wybierz transakcję z tabeli 'Historia wypożyczeń'.");
			
			alert.showAndWait();
		}
	}
	
	private void handleNewRent(){
		Rent newRent = new Rent();
		boolean okClicked = mainApp.showRentAddNewDialog(newRent);
		if(okClicked){
			mainApp.getRentRep().add(newRent);
		}
		
	}
	
	@FXML
	private void handleNew(){
		switch(selectedTab){
			case 1:
				handleNewMovie();
				break;
			case 2:
				handleNewClient();
				break;
			case 3:
				handleNewRent();
				break;
		}
	}
	
	@FXML 
	void handleEdit(){
		switch(selectedTab){
		case 1:
			handleEditMovie();
			break;
		case 2:
			handleEditCliet();
			break;
		case 3:
			handleEditRent();
			break;
		}
	}
	
	private void showMovieDetails(Movie movie){
		if(movie != null){
			mainApp.showMovieDetails(movie);
		}
	}
	
	private void showClientDetails(Client client){
		if(client != null){
			mainApp.showClientDetails(client);
		}
	}
	
	private void showRentDetails(Rent rent){
		if(rent != null){
			mainApp.showRentDetails(rent);
		}
	}
	
	@FXML
	private void selectMovieTable(){
		selectedTab = 1;
	}
	@FXML
	private void selectClientTable(){
		selectedTab = 2;
	}
	@FXML
	private void selectRentTab(){
		selectedTab = 3;
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		movieTable.setItems(mainApp.getMovieRep());
		clientTable.setItems(mainApp.getClientRep());
		rentTable.setItems(mainApp.getRentRep());
	}
}
