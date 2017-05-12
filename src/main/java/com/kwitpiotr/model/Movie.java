package com.kwitpiotr.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	
	private final StringProperty title;
	private final IntegerProperty manufactureYear;
	private final StringProperty genre;
	private final BooleanProperty rented;
	
	public Movie(){
		//id = idNum;
		//idNum++;
		this(null, 0, null);
		//rented = new SimpleBooleanProperty(false);
	}
	public Movie(String title, int manufactureYear, String genre){
		this.title = new SimpleStringProperty(title);
		this.manufactureYear = new SimpleIntegerProperty(manufactureYear);;
		this.genre = new SimpleStringProperty(genre);
		//id = idNum;
		//idNum++;
		rented = new SimpleBooleanProperty(false);
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String name) {
		this.title.set(name);
	}
	public StringProperty titleProperty(){
		return title;
	}
	public int getManufactureYear() {
		return manufactureYear.get();
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear.set(manufactureYear);
	}
	public IntegerProperty manufactureYearProperty(){
		return manufactureYear;
	}
	public String getGenre() {
		return genre.get();
	}
	public void setGenre(String genre) {
		this.genre.set(genre);
	}
	public StringProperty genreProperty(){
		return genre;
	}

	public boolean isRented() {
		return rented.get();
	}
	public String getRented(){
		if(isRented())
			return "Wypożyczony";
		else return "Dostępny";
	}
	public void setRented(boolean rented) {
		this.rented.set(rented);
	}
	public BooleanProperty rentedProperty(){
		return rented;
	}
	/*public int getId(){
		return id;
	}*/
}
