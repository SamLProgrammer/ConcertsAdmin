package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Concert {
	
	private String id;
	private String name;
	private Ticket[] ticketsList;	
	private double price;
	private Calendar date;
	
	public Concert(String id, String name, double price, Calendar date, int slot) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
		ticketsList = new Ticket[slot];
		initTickets();
	}
	
	private void initTickets() {
		for (int i = 0; i < ticketsList.length; i++) {
			ticketsList[i] = new Ticket(id + "-" + (i+1));
		}
	}
	
	public Concert(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		date = GregorianCalendar.getInstance();
	}

	private int freeTicketsCount() {
		int counter = 0;
		for (int i = 0; i < ticketsList.length; i++) {
			if(ticketsList[i].isAvailable()) {
				counter++;
			}
		}
		return counter;
	}
	
	public String[] dataToVector() {
		String[] dataVector = {id, name, "" + date.get(Calendar.DAY_OF_MONTH) + " - " + date.get(Calendar.MONTH)
							    + " - " +  date.get(Calendar.YEAR), "" + freeTicketsCount(), "" + price};
		return dataVector;
	}
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public Calendar getDate() {
		return date;
	}
	
	public String getId() {
		return id;
	}

	public Ticket[] getTicketsList() {
		return ticketsList;
	}
}
