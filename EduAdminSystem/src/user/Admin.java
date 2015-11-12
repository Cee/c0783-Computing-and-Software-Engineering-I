package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import console.Io;


public class Admin extends User{
	
	public static void go(){
		
		String line = "";		
		Scanner inputScanner = new Scanner(System.in);		
		
		while(!(line.toLowerCase().equals("end"))){
			System.out.println("----------Administration-----------");
			line = inputScanner.nextLine();
			String cmd = analyseCmd(line);
			String[] split = line.split(" ");
			String list;
			int num;
			switch (cmd) {
			case "ChangePassword":				
				String oldPassword = split[1];
				String newPassword = split[2];
				changePassword(oldPassword,newPassword);
				break;
			case "Show":
				list = split[1];
				show(list);
				break;
			case "Delete":
				list = split[1];
				num = Integer.parseInt(split[2]);
				delete(list,num);
				break;
			default:
				break;
			}
		}
		
		inputScanner.close();
	}
	
	
	private static String analyseCmd(String line){
		
		String[] split = line.split(" ");			
		return split[0]; 
		
	}
	
	private static void changePassword(String oldPassword, String newPassword) {
		
		Io.setNewPassword("admin",newPassword);
		
	}
	
	private static void show(String list){
		
		try{
			
			BufferedReader br = new BufferedReader(new FileReader("data/"+list+".txt"));
			int count = 0;
			String line;
			while((line = br.readLine())!=null){
				count++;
				System.out.println(count+" "+line);
			}
			br.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}

		
	}
	
	private static void delete(String list,int num){
		
		try{
			ArrayList<String> content = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader("data/"+list+".txt"));
			int count = 0;
			String line;
			while((line = br.readLine())!=null){
				count++;
				if(count != num){
					content.add(line);
				}
			}
			br.close();
			
			File file = new File("data/"+list+".txt");
			FileWriter fw =  new FileWriter(file);
			fw.write("");
			fw.close();
			
			FileWriter writer = new FileWriter("data/"+list+".txt");
			for(int i = 0; i < content.size(); i++){
				writer.write(content.get(i)+"\n");
			}

			writer.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}

	}
		
}
