package com.kwitpiotr;

import java.time.LocalDate;

/*public class Rent {
	private static int idNum = 1;
	private int id;
	private Movie movie;
	private Client client;
	private LocalDate start;
	private LocalDate end;
	private boolean returned;
	
	public Rent(Movie movie, Client client){
		id = idNum;
		id++;
		this.movie = movie;
		this.client = client;
		start = LocalDate.now();
		returned = false;
		this.movie.setRented(true);
	}

	public int getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	public void returnRent(){
		setReturned(true);
		setEnd(LocalDate.now());
		movie.setRented(false);
	}
	
	public String toString(){
		String returnDate = "";
		if(returned == true)
			returnDate = end.toString();
		return id + ". " + movie.getName() + " rented by " + client.toString() +
				" in " + start.toString() + ". Returned: " + returned + " " + returnDate;
	}
}*/
