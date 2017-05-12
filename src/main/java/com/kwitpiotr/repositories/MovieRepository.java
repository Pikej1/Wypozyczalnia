package com.kwitpiotr.repositories;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.kwitpiotr.model.Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieRepository {
	private ObservableList<Movie> movieRepository;
	private final File file;

	public MovieRepository(){
		movieRepository = FXCollections.observableArrayList();
		//file = new File(dirPath + "//WypozyczalniaPiotrKwit//src//main//resources//movieDataBase.xml");
		file = new File(getDirPath() + "src//main//resources//movieDataBase.xml");
		System.out.println(file.getAbsolutePath());
		/*movieRepository.add(new Movie("Matrix", 1999, "Sci-fi/action"));
    	movieRepository.add(new Movie("Schrek",2001, "Comedy"));
    	movieRepository.add(new Movie("Fightclub", 1999, "Thriller"));
    	Movie movie = new Movie("Gwiezdne Wojny: Część IV - Nowa nadzieja", 1977, "Sci-Fi");
    	movie.setRented(true);
    	movieRepository.add(movie);
    	saveDataToFile();*/
		loadRepositoryFromFile();
	}
	
	public void loadRepositoryFromFile(){
		try {
			JAXBContext context = JAXBContext.newInstance(MovieListWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			//Reading XML from file
			MovieListWrapper wrapper = (MovieListWrapper) unmarshaller.unmarshal(file);
			
			movieRepository.clear();
			movieRepository.addAll(wrapper.getMovieList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveDataToFile(){
		try{
			JAXBContext context = JAXBContext.newInstance(MovieListWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			MovieListWrapper wrapper = new MovieListWrapper();
			wrapper.setMovieList(movieRepository);
			marshaller.marshal(wrapper, file);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void add(Movie movie){
		movieRepository.add(movie);
	}
	
	public ObservableList<Movie> getRepository(){
		return movieRepository;
	}
	
	public ObservableList<Movie> getAvaiable(){
		ObservableList<Movie> avaiable = FXCollections.observableArrayList();
		for(Movie movie: movieRepository){
			if(!movie.isRented()){
				avaiable.add(movie);
			}
		}
		return avaiable;
	}

	/**
	 * Getting absolute path of repository file to avoid troubles. There's two different paths in Eclipse and IntelliJ.
	 * Moreover the path will be different after downloading application directly from GitHUB. This method seems to be
	 * little cheesy but works. And as said my mate: "If something looks stupid but works, it's no longer stupid".
	 * @return absolute path of directory file
	 */
	private String getDirPath() {
		File tempFile = new File("");
		String dirPath = tempFile.getAbsolutePath();
		String tab[] = dirPath.split("\\\\");
		if(tab[tab.length-1].equals(tab[tab.length-2])){
			dirPath += "//";
		}else dirPath += "//" + tab[tab.length-1] + "//";
		return dirPath;
	}
}
