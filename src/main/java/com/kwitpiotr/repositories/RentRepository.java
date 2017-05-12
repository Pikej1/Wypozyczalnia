package com.kwitpiotr.repositories;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.kwitpiotr.model.Rent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RentRepository {
	private ObservableList<Rent> rentRep;
	private final File file;

	public RentRepository(){
		rentRep = FXCollections.observableArrayList();
		//file = new File("WypozyczalniaPiotrKwit//src//main//resources//rentDataBase.xml");
		file = new File(getDirPath() + "src//main//resources//rentDataBase.xml");

		loadRepositoryFromFile();
	}
	
	public void loadRepositoryFromFile(){
		try {
			JAXBContext context = JAXBContext.newInstance(RentListWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			//Reading XML from file
			RentListWrapper wrapper = (RentListWrapper) unmarshaller.unmarshal(file);
			
			rentRep.clear();
			rentRep.addAll(wrapper.getRentList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void saveDataToFile(){
		try{
			JAXBContext context = JAXBContext.newInstance(RentListWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			RentListWrapper wrapper = new RentListWrapper();
			wrapper.setRentList(rentRep);
			marshaller.marshal(wrapper, file);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void add(Rent rent){
		rentRep.add(rent);
	}
	
	public ObservableList<Rent> getRepository(){
		return rentRep;
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
