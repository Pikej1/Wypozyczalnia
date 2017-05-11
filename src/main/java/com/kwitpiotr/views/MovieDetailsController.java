package com.kwitpiotr.views;

import com.kwitpiotr.model.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieDetailsController {

	@FXML
	private Label titleLabel;
	@FXML
	private Label manufYearLabel;
	@FXML
	private Label genreLabel;
	@FXML
	private Label availableLabel;

	public MovieDetailsController(){}
	
	public void showMovieDetails(Movie movie){
		if(movie != null){
			titleLabel.setText(movie.getTitle());
			manufYearLabel.setText(Integer.toString(movie.getManufactureYear()));
			genreLabel.setText(movie.getGenre());
			availableLabel.setText(movie.getRented());
		}else{
			titleLabel.setText("");
			manufYearLabel.setText("");
			genreLabel.setText("");
			availableLabel.setText("");
		}
	}
	
	//@FXML
	//public void initialize(){}
}
