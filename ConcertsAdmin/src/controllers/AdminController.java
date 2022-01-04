package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import models.Concert;
import models.ConcertsManager;
import network.AdminClient;
import views.AdminFrame;

public class AdminController implements ActionListener{
	
	private AdminClient adminClient;
	private ConcertsManager concertsManager;
	private AdminFrame adminFrame;
	private Timer notifyTimer;
	
	public AdminController()  {
		concertsManager = new ConcertsManager();
		adminFrame = new AdminFrame(this);
		try {
			adminClient = new AdminClient(new ClientListener(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_CONCERT_BTN:
				addConcert();
			break;
		}
	}
	
//=============================================== UTILITIES ================================================
	
	private void addConcert() {
		Concert addedConcert = getConcertFromGUI();
		concertsManager.addConcert(addedConcert);
		updateConcertsTable();
		addNewConcertToServer(concertsManager.encodeConcertToJson(addedConcert));
	}
	
	private Concert getConcertFromGUI() {
		return concertsManager.createConcert(adminFrame.getIdFieldData(),
				adminFrame.getNameFieldData(),
				adminFrame.getPriceFieldData(),
				adminFrame.getSelectedDate(),
				adminFrame.getSlotsAmount());
	}
	
	public void updateFullData(String string) {
		if(!string.equalsIgnoreCase("null")) {
			concertsManager.updateFullData(string);
			adminFrame.updateTable(concertsManager.getConcertsList());
		}
	}
	
	public void notifyFullDataWriten() {
		if(notifyTimer == null || !notifyTimer.isRunning()) {
			notifyTimer = new Timer(3000, new ActionListener() {
				int counter = 0;
				@Override
				public void actionPerformed(ActionEvent e) {
					if(counter >= 1) {
						adminFrame.clearNotificationsLabel();
						notifyTimer.stop();
					}
					else {
					adminFrame.notifyFullDataWriten();
					counter++;
					}
				}
			});
			notifyTimer.start();
		}
	}
	
	private void updateConcertsTable() {
		adminFrame.updateTable(concertsManager.getConcertsList());
	}
	
	private void addNewConcertToServer(String jsonString) {
		try {
			adminClient.sendUpdates(jsonString);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
