package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;


public class ClientHelper {
	
	private String host = "localhost";
	private int port = 8888;
	public ClientHelper(){
		
	}
	
	public String sendToNet(String send){
		Socket s = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		String reply = "";
		try{
			s= new Socket(host,port);
			
			pw = new PrintWriter(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			pw.write(send);
			pw.flush();
			s.shutdownOutput();
			
			reply = br.readLine();
			System.out.println(reply);
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				br.close();
				pw.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reply;
	}
	
	public void listToNet(ArrayList<Serializable> rawList){
		Socket s = null;
		ObjectOutputStream out;
		try{
			s= new Socket(host,port);
			
			out = new ObjectOutputStream(s.getOutputStream());
	        out.writeObject(rawList);
			out.flush();
//			out.close();
			
//			s.shutdownOutput();
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Object getObject(String send){
		Socket s = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		Object reply = null;
		try{
			s= new Socket(host,port);
			
			pw = new PrintWriter(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			pw.write(send);
			pw.flush();
			s.shutdownOutput();
			
			ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
			try {
				reply = objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			reply = br.readLine();
//			System.out.println(reply);
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				br.close();
				pw.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reply;
	}	

	

	
	public static void main(String args[]){
//		ClientHelper ch = new ClientHelper();
	}

}
