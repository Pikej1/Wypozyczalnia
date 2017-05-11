package com.kwitpiotr.repositories;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kwitpiotr.model.Client;

@XmlRootElement(name = "clients")
public class ClientListWrapper {
	
	private List<Client> clientsList;
	
	@XmlElement(name = "client")
	public List<Client> getClientsList(){
		return clientsList;
	}
	
	public void setClientsList(List<Client> clientsList){
		this.clientsList = clientsList;
	}
}
