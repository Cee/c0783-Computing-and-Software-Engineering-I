package main;

import net.Client;

public class MainSystem {
	
	Login loginFrame;
	Client client;

	public MainSystem() {
		
		client = new Client();
		loginFrame = new Login(client);
		loginFrame.setVisible(true);

	}

	public static void main(String[] args) {
		new MainSystem();
	}

}
