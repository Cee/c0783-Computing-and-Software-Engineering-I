package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	private ServerSocket serverSocket;
	
	
	public Server(){
		serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			socket = serverSocket.accept();
			System.out.println("Accepted");
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream());
							
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void sendMessage(String message){	
		printWriter.println(message);
		printWriter.flush();					
	}
	

	public String receiveMessage(String send){
		sendMessage(send);
		String reply = null;
		try {
			reply = bufferedReader.readLine();
			System.out.println("Reply is:"+reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	public String receiveMessage(){
		String reply = null;
		try {
			while((reply = bufferedReader.readLine())!= null){
				System.out.println("Reply is:"+reply);
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return reply;
	}
	
}
