package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	private ServerSocket serverSocket;
	ObjectOutputStream out;
	ObjectInputStream objectInputStream;
	
	
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
	
	public void sendObject(Object object){
		try {
			System.out.println("Sending...");
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
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
	
	public Object getObject(){		
		Object reply = null;
		try {
			try {
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				reply = objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return reply;
	}
	
}
