package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	ObjectInputStream objectInputStream;
	ObjectOutputStream out;
	
	
	public Client(){
		link();
	}
	
	public void link() {
		
		try {			
			
			socket = new Socket("localhost",8888);
			printWriter = new PrintWriter(socket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
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
	
	public void sendObject(Object object){
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
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
	
	public String getMessage(){
		String message = null;
		try {
			message = bufferedReader.readLine();
			System.out.println(message);				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public void close(){
		try{
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


}
