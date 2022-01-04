package controllers;

public class ClientListener {

	private AdminController controller;
	
	public ClientListener(AdminController controller) {
		this.controller = controller;
	}
	
	public void updateFullData(String string) {
		controller.updateFullData(string);
	}
	
	public void notifyFullDataWriten() {
		controller.notifyFullDataWriten();
	}
}
