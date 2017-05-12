package com.kwitpiotr.repositories;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.kwitpiotr.model.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientRepository {
	private ObservableList<Client> clientRep;
	private final File file;

	public ClientRepository(){
		clientRep = FXCollections.observableArrayList();
    	file = new File(getDirPath() + "src//main//resources//clientDataBase.xml");
    	loadRepositoryFromFile();
	}
	
	public void loadRepositoryFromFile(){
		try {
			JAXBContext context = JAXBContext.newInstance(ClientListWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			//Reading XML from file
			ClientListWrapper wrapper = (ClientListWrapper) unmarshaller.unmarshal(file);
			
			clientRep.clear();
			clientRep.addAll(wrapper.getClientsList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveDataToFile(){
		try{
			JAXBContext context = JAXBContext.newInstance(ClientListWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			ClientListWrapper wrapper = new ClientListWrapper();
			wrapper.setClientsList(clientRep);
			
			marshaller.marshal(wrapper, file);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void add(Client client){
		clientRep.add(client);
	}
	
	public ObservableList<Client> getRepository(){
		return clientRep;
	}

	private String getDirPath() {
		File tempFile = new File("");
		String dirPath = tempFile.getAbsolutePath();
		String tab[] = dirPath.split("\\\\");
		if(!tab[tab.length-1].equals(tab[tab.length-2])){
			dirPath += "//" + tab[tab.length-1];
		}
		return dirPath + "//";
	}
}