package com.kwitpiotr.repositories;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kwitpiotr.model.Rent;

@XmlRootElement(name = "rents")
public class RentListWrapper {
	
	private List<Rent> rentList;
	
	@XmlElement(name = "rent")
	public List<Rent> getRentList(){
		return rentList;
	}

	public void setRentList(List<Rent> rentList){
		this.rentList = rentList;
	}
}
