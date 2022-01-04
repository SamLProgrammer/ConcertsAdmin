package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controllers.Actions;

public class FormPanel extends JPanel{
	
	private RoundJTextField concertNameField;
	private RoundJTextField concertIDField;
	private RoundJTextField concertPriceField;
	private RoundJTextField concertDateField;
	private RoundJTextField concertSlotsAmount;
	private int UIWidth;
	private int UIHeight;
	private Image img;
	private JDateChooser dateChooser;
	
	public FormPanel(ActionListener listener) {
		this.UIWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.UIHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		img = new ImageIcon(getClass().getResource("/img/formBackground2.jpg")).getImage();
		setLayout(new GridLayout(2,1));
		initComponents(listener);
	}
	
	private void initComponents(ActionListener listener) {
		setUIManager();
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel textFieldsPanel = new JPanel();
		textFieldsPanel.setLayout(new GridBagLayout());
		textFieldsPanel.setOpaque(false);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridBagLayout());
		btnPanel.setOpaque(false);
		DCButton addBtn = new DCButton("#FF8C00");
		addBtn.addActionListener(listener);
		addBtn.setActionCommand(Actions.ADD_CONCERT_BTN.name());
		addBtn.setText("ADD");
		addBtn.setFont(new Font("Oswald", Font.BOLD, UIHeight/40));
		btnPanel.add(addBtn);
		addBtn.setPreferredSize(new Dimension(UIWidth/10, UIHeight/22));
		
		initFormFields();
		initDateChooser();
		
		JPanel idFormPanel = new JPanel();
		idFormPanel.setLayout(new BoxLayout(idFormPanel, BoxLayout.X_AXIS));
		idFormPanel.setOpaque(false);
		JPanel nameFormPanel = new JPanel();
		nameFormPanel.setLayout(new BoxLayout(nameFormPanel, BoxLayout.X_AXIS));
		nameFormPanel.setOpaque(false);;
		JPanel dateFormPanel = new JPanel();
		dateFormPanel.setLayout(new BoxLayout(dateFormPanel, BoxLayout.X_AXIS));
		dateFormPanel.setOpaque(false);
		JPanel priceFormPanel = new JPanel();
		priceFormPanel.setLayout(new BoxLayout(priceFormPanel, BoxLayout.X_AXIS));
		priceFormPanel.setOpaque(false);
		JPanel slotsFormPanel = new JPanel();
		slotsFormPanel.setLayout(new BoxLayout(slotsFormPanel, BoxLayout.X_AXIS));
		slotsFormPanel.setOpaque(false);
		
		JLabel idFieldLabel = new JLabel("Concert Id:  ");
		JLabel nameFieldLabel = new JLabel("Concert Name:  ");
		JLabel priceFieldLabel = new JLabel("Concert Price:  ");
		JLabel dateFieldLabel = new JLabel("Concert Date:  ");
		JLabel slotsFieldLabel = new JLabel("Concert Slots: ");
		
		idFormPanel.add(idFieldLabel);
		idFormPanel.add(concertIDField);
		nameFormPanel.add(nameFieldLabel);
		nameFormPanel.add(concertNameField);
		dateFormPanel.add(dateFieldLabel);
		dateFormPanel.add(dateChooser);
		priceFormPanel.add(priceFieldLabel);
		priceFormPanel.add(concertPriceField);
		slotsFormPanel.add(slotsFieldLabel);
		slotsFormPanel.add(concertSlotsAmount);
		
		
		gbc.weightx = 1;
		gbc.insets = new Insets(0, UIWidth/80, 0, UIWidth/80);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		textFieldsPanel.add(idFormPanel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 4;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		textFieldsPanel.add(nameFormPanel,gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1.8;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		textFieldsPanel.add(priceFormPanel,gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		textFieldsPanel.add(dateFormPanel,gbc);
		
		gbc.gridx = 9;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.8;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		textFieldsPanel.add(slotsFormPanel,gbc);
		
		add(textFieldsPanel);
		add(btnPanel);
		
	}
	
	private void initDateChooser() {
		Calendar c = GregorianCalendar.getInstance();
		Date date = new GregorianCalendar(c.get(Calendar.YEAR)+1,
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)).getTime();
		dateChooser = new JDateChooser();
		dateChooser.setOpaque(false);
		dateChooser.setMaxSelectableDate(date);
		dateChooser.setFont(new Font("Oswald", Font.BOLD, UIHeight/80));
		((JTextFieldDateEditor)dateChooser.getDateEditor()).setOpaque(false);
 		((JTextFieldDateEditor)dateChooser.getDateEditor()).setEditable(false);
 		((JTextFieldDateEditor)dateChooser.getDateEditor()).setHorizontalAlignment(SwingConstants.CENTER);
 		((JTextFieldDateEditor)dateChooser.getDateEditor()).setFont(new Font("Unispace", Font.BOLD, UIHeight/40));
	}
	
	private void initFormFields() {
		concertNameField = new  RoundJTextField(10);
		concertNameField.setOpaque(false);
		concertNameField.setText("aaaa");
		concertIDField = new  RoundJTextField(10);
		concertIDField.setOpaque(false);
		concertIDField.setText("123");
		concertPriceField = new  RoundJTextField(10);
		concertPriceField.setOpaque(false);
		concertPriceField.setText("1000");
		concertDateField = new  RoundJTextField(10);
		concertDateField.setOpaque(false);
		concertSlotsAmount = new RoundJTextField(10);
		concertSlotsAmount.setOpaque(false);
		concertSlotsAmount.setText("10");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), this);
		
	}
	
	private void setUIManager() {
		UIManager.put("Label.font", new Font("Oswald", Font.BOLD, UIHeight/40));
		UIManager.put("TextField.font", new Font("Oswald", Font.PLAIN, UIHeight/40));
		UIManager.put("TextField.foreground", Color.decode("#FFFFFF"));
		UIManager.put("TextField.border", BorderFactory.createEmptyBorder(UIHeight/200,UIHeight/200,UIHeight/200,UIHeight/200));
		UIManager.put("Label.foreground", Color.decode("#ffffff"));
	}
	
	public String getNameFieldData() {
		return concertNameField.getText();
	}
	
	public String getIdFieldData() {
		return concertIDField.getText();
	}
	
	public double getPriceFieldData() {
		return Double.valueOf(concertPriceField.getText());
	}
	
	public Calendar getSelectedDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateChooser.getDate());
		return cal;
	}
	
	public int getSlotsAmount() {
	return Integer.valueOf(concertSlotsAmount.getText());	
	}
}
