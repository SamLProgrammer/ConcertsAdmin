package models;

import java.util.ArrayList;
import java.util.Calendar;

import persistence.JSONManager;

public class ConcertsManager {

	private ArrayList<Concert> concertsList;
	private JSONManager json;
	
	public ConcertsManager() {
		concertsList = new ArrayList<>();
		json = new JSONManager();
	}
	
	public Concert createConcert(String id, String name, double price, Calendar date, int slots) {
		return new Concert(id, name, price, date, slots);
	}
	
	public void addConcert(Concert concert) {
		concertsList.add(concert);
	}
	
	public void removeConcert(String id) {
		for (Concert concert : concertsList) {
			if(concert.getId() == id) {
				concertsList.remove(concert);
			}
		}
	}

	public ArrayList<Concert> getConcertsList() {
		return concertsList;
	}
	
	public String encodeConcertToJson(Concert concert) {
		return json.encodeConcert(concert);
	}
	
	public void updateFullData(String string) {
		concertsList =  json.decodeFullData(string);
	}
}
