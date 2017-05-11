package com.kwitpiotr;

import com.kwitpiotr.model.Client;
import com.kwitpiotr.model.Movie;
import com.kwitpiotr.model.Rent;
import com.kwitpiotr.repositories.ClientRepository;
import com.kwitpiotr.repositories.MovieRepository;
import com.kwitpiotr.repositories.RentRepository;
import com.kwitpiotr.views.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	//private ObservableList<Movie> movieRep = FXCollections.observableArrayList();
	private MovieRepository movieRep = new MovieRepository();
	private ClientRepository clientRep = new ClientRepository();
	private RentRepository rentRep = new RentRepository();
	//private ObservableList<Client> clientRep = FXCollections.observableArrayList();
	//private ObservableList<Rent> rentRep = FXCollections.observableArrayList();
	
	
	public MainApp() {
		// TODO Auto-generated constructor stub
    	
    	/*clientRep.add(new Client("John", "Smith", LocalDate.of(1980, 4, 12), 11100011));
    	clientRep.add(new Client("Antoni", "Macierewicz", LocalDate.of(1948, 9, 3), 11111111));
    	clientRep.add(new Client("Andrzej", "Dupa", LocalDate.of(1970, 1, 1),99999999));
    	*/
    	//rentRep.add(new Rent(movieRep.getRepository().get(0), clientRep.getRepository().get(1)));
    	//rentRep.add(new Rent(movieRep.getRepository().get(1), clientRep.getRepository().get(0)));
    	//rentRep.get(0).returnItem();
	}
	
	private void initMainLayout() {
		try{
			//Loading MainLayout.fxml
			rootLayout = new BorderPane();
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(ClassLoader.getSystemResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Controller
			RootController controller = loader.getController();
			controller.setMainApp(this);

			
			//Showing the scene with the layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				updateData();
			});
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void updateData(){
		movieRep.saveDataToFile();
		clientRep.saveDataToFile();
		rentRep.saveDataToFile();
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Rental System");
		
		initMainLayout();
	}

	/**
	 * Showing details of chosen client
	 * @param client which details are presented
	 */
	public void showClientDetails(Client client) {
		try{
			//Loading ClientDetails.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("ClientDetails.fxml"));
			AnchorPane clientDetails = (AnchorPane) loader.load();
			
			//Controller
			ClientDetailsController controller = loader.getController();
			controller.showClientDetails(client);
			
			//Attaching ClientDetails.fxml to main layout
			rootLayout.setCenter(clientDetails);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Showing details of chosen movie
	 * @param movie which details are presented
	 */
	public void showMovieDetails(Movie movie){
		try{
			//Loading MovieDetails.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("MovieDetails.fxml"));
			AnchorPane movieDetails = (AnchorPane) loader.load();
			
			//Controller
			MovieDetailsController controller = loader.getController();
			controller.showMovieDetails(movie);
			
			//Attaching MovieDetails.fxml to main layout
			rootLayout.setCenter(movieDetails);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void showRentDetails(Rent rent){
		try{
			//Loading MovieDetails.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("RentDetails.fxml"));
			AnchorPane movieDetails = (AnchorPane) loader.load();
			
			//Controller
			RentDetailsController controller = loader.getController();
			controller.showRentDetails(rent);
			
			//Attaching MovieDetails.fxml to main layout
			rootLayout.setCenter(movieDetails);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean showClientEditDialog(Client client){
		try{
			//Loading .fxml and creating new stage (for popuping window)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("ClientEditDialog.fxml"));
			Pane window = (Pane) loader.load();
			
			//Creating dialog Stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj dane klienta");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(window);
	        dialogStage.setScene(scene);
	        
	        //Set client in contr
	        ClientEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setClient(client);
	        
	        dialogStage.showAndWait();
	        return controller.isConfirmed();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showMovieEditDialog(Movie movie){
		try{
			//Loading .fxml and creating new stage (for popping window)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("MovieEditDialog.fxml"));
			Pane window = (Pane) loader.load();
			
			//Creating dialog Stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edycja filmu");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(window);
	        dialogStage.setScene(scene);
	        
	        //Set client in controller
	        MovieEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setMovie(movie);
	        
	        dialogStage.showAndWait();
	        return controller.isConfirmed();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showRentEditDialog(Rent rent){
		try{
			//Loading .fxml and creating new stage (for popping window)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("RentEditDialog.fxml"));
			Pane window = (Pane) loader.load();
			
			//Creating dialog Stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Zwrot filmu");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(window);
	        dialogStage.setScene(scene);
	        
	        //Set client in controller
	        RentEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setRent(rent);
	        
	        dialogStage.showAndWait();
	        return controller.isConfirmed();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showRentAddNewDialog(Rent rent){
		try{
			//Loading .fxml and creating new stage (for popping window)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("RentAddNewDialog.fxml"));
			Pane window = (Pane) loader.load();
			
			//Creating dialog Stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wypożycz film");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(window);
	        dialogStage.setScene(scene);
	        
	        //Set client in controller
	        RentAddNewDialogController controller = loader.getController();
	        controller.setRepository(this);
	        controller.setDialogStage(dialogStage);
	        controller.setRent(rent);
	        
	        dialogStage.showAndWait();
	        return controller.isConfirmed();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Movie> getMovieRep() {
		return movieRep.getRepository();
	}
	public ObservableList<Movie> getAvaiableMovies(){
		return movieRep.getAvaiable();
	}
	public ObservableList<Client> getClientRep() {
		return clientRep.getRepository();
	}
	public ObservableList<Rent> getRentRep(){
		return rentRep.getRepository();
	}
}
