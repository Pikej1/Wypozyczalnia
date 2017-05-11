package com.kwitpiotr.repositories;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kwitpiotr.model.Movie;

@XmlRootElement(name = "movies")
public class MovieListWrapper {

	private List<Movie> movieList;
	
	@XmlElement(name = "movie")
	public List<Movie> getMovieList(){
		return movieList;
	}
	
	public void setMovieList(List<Movie> movieList){
		this.movieList = movieList;
	}
}
