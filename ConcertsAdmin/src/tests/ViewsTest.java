package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.AdminFrame;

public class ViewsTest {
	
	public static void main(String[] args) {
		new AdminFrame(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
