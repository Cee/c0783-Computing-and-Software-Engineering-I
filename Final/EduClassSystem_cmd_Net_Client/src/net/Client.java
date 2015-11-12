package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	
	
	public Client(){
		link();
	}
	
	class ReceiveThread implements Runnable {
		public void run() {
			String message;
			try {
				while ((message=bufferedReader.readLine())!=null) {
					System.out.println(message);	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void link() {
		
		try {			
			socket = new Socket("localhost",8888);
			printWriter = new PrintWriter(socket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new Thread(new ReceiveThread()).start();	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(String message) {
		printWriter.println(message);
		printWriter.flush();
	}

	public static void main(String[] args) {
		new Client();
	}


}
