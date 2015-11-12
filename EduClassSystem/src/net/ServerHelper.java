package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import user.UserList;

import course.CourseList;
import course.ScoreList;

import main.MainSystem;
import instruction.Instruction;

public class ServerHelper {
	
	int port = 8888;
	private ServerSocket ss = null;
	
	public ServerHelper(){
		
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void startServer(){
		
		MainSystem system = new MainSystem();
		Instruction instruction = new Instruction();
		
		Socket s = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		while(true){
			try{
				s = ss.accept();
				
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw = new PrintWriter(s.getOutputStream());			
	
				
				String read_str = br.readLine();
				System.out.println("the request from client is :" + read_str);
				
				String reply = null;
				String[] split = read_str.split(" ");
				
				UserList userList;
				CourseList courseList;
				ScoreList scoreList;
				ObjectOutputStream out;
				
				switch (split[0]) {
				case "Login":
					reply = system.netCheck(read_str);
					System.out.println("The replay is " +  reply);					
					pw.write(reply);
					pw.flush();
					break;
				case "getList":
					userList = system.getUserList();
					courseList = system.getCourseList();
					scoreList = system.getScoreList();
					ArrayList<Serializable> rawList = new ArrayList<Serializable>();
					rawList.add(userList);
					rawList.add(courseList);
					rawList.add(scoreList);
					out = new ObjectOutputStream(s.getOutputStream());
			        out.writeObject(rawList);
					out.flush();
					out.close();
					break;
				case "getUserList":
					userList = system.getUserList();
					out = new ObjectOutputStream(s.getOutputStream());
			        out.writeObject(userList);
					out.flush();
					out.close();
					break;
				case "getScoreList":
					scoreList = system.getScoreList();
					out = new ObjectOutputStream(s.getOutputStream());
			        out.writeObject(scoreList);
					out.flush();
					out.close();
					break;
				case "getCourseList":
					courseList = system.getCourseList();
					out = new ObjectOutputStream(s.getOutputStream());
			        out.writeObject(courseList);
					out.flush();
					out.close();
					break;
				case "sendCourseList":
					system.setCourseList((CourseList)(getList(s).get(0)));	
					System.out.println(1);
				default:
					break;
				}				
			}catch(IOException ioe){
				ioe.printStackTrace();
			}finally{
				try{
					br.close();
					pw.close();
					s.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Serializable> getList(Socket s){
		ArrayList<Serializable> rawList = new ArrayList<Serializable>();
		BufferedReader br = null;
		Object reply = null;
		try{
				
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));				
			ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
			try {
				reply = objectInputStream.readObject();
				rawList = (ArrayList<Serializable>) reply;
				System.out.println("get it");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rawList;
	}
	
	public void stopServer(){
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ServerHelper sHelper = new ServerHelper();
		sHelper.startServer();
	}

}
