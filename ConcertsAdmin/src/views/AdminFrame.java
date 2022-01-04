package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import models.Concert;

public class AdminFrame extends JFrame{
	
	private FormPanel formPanel;
	private ConcertsTablePanel concertsTablePanel;
	private JLabel notificationsLabel;
	
	public AdminFrame(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		initComponents(listener);
		setVisible(true);
	}
	
	private void initComponents(ActionListener listener) {
		formPanel = new FormPanel(listener);
		setIconImage(new ImageIcon(getClass().getResource("/img/spotlights.png")).getImage());
		concertsTablePanel = new ConcertsTablePanel();
		notificationsLabel = new JLabel("",SwingConstants.CENTER);
		notificationsLabel.setForeground(Color.BLACK);
		add(formPanel, BorderLayout.NORTH);
		add(concertsTablePanel, BorderLayout.CENTER);
		add(notificationsLabel, BorderLayout.SOUTH);
	}
	
	public String getNameFieldData() {
		return formPanel.getNameFieldData();
	}
	
	public String getIdFieldData() {
		return formPanel.getIdFieldData();
	}
	
	public double getPriceFieldData() {
		return formPanel.getPriceFieldData();
	}
	
	public Calendar getSelectedDate() {
		return formPanel.getSelectedDate();
	}

	public int getSlotsAmount() {
		return formPanel.getSlotsAmount();
	}
	
	public void notifyFullDataWriten() {
		notificationsLabel.setText("<html>FULL DATA UPDATED <br>IN SERVER<br></html>");
	}
	
	public void clearNotificationsLabel() {
		notificationsLabel.setText("");
		repaint();
	}
	
	public void updateTable(ArrayList<Concert> concertsList) {
		concertsTablePanel.updateRowsTable(concertsList);
	}
}
