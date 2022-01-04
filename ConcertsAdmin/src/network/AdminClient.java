package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import controllers.ClientListener;
import controllers.Requests;

public class AdminClient{
	
	private Socket socket; 
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	private boolean clientUp;
	private ClientListener clientListener;
	
	public AdminClient(ClientListener clientListener) throws UnknownHostException, IOException {
		this.clientListener = clientListener;
		socket = new Socket("localhost", 3000);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		clientUp = true;
		updateConcertsList();
		askForUpdates();
	}
	
	public void askForUpdates() throws IOException {
		outputStream.writeUTF(Requests.ASK_FOR_FULL_DATA.toString());
	}
	
	public void sendUpdates(String newConcertAsJson) throws IOException {
		outputStream.writeUTF(Requests.UPDATE_SERVER_DATA.toString());
		outputStream.writeUTF(newConcertAsJson);
	}
	
	private void updateConcertsList() {	
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(clientUp) {
					try {
						if(inputStream.available() > 0) {
							String string = inputStream.readUTF();
							switch (Requests.valueOf(string)) {
							case FULL_DATA_WRITEN:
								clientListener.notifyFullDataWriten();
								break;
							case FULL_DATA_TO_ADMIN:
								clientListener.updateFullData(inputStream.readUTF());
								break;
							case UPDATE_30:
								inputStream.readUTF();
								break;
							case SERVER_TO_CLIENT_CONCERTS:
								inputStream.readUTF();
								break;
							default:
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}