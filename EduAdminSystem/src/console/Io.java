package console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Io {
	
	
	public static String checkGroup(String id){
		
		BufferedReader br;
		String line;	
		String group = null;
		
		try{
			
			br = new BufferedReader(new FileReader("data/group.txt"));
			
			while((line = br.readLine())!=null){
				
				String[] split = line.split(" ");
				if (split[0].equals(id)){
					group = split[1];
					break;
				}
			}			
			
			br.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return group;
	}
	
	public static int checkData(String id, String password, String group){		
		
		BufferedReader br;
		String line;
		
		if (group == null){
			return 2;
		}
		
		try{
			
			br = new BufferedReader(new FileReader("data/"+group+".txt"));				
			
			while((line = br.readLine())!=null){
				
				String[] split = line.split(" ");
				if (split[0].equals(id)){
					if (split[1].equals(password)){
						br.close();
						return 0;
					}
					else{
						br.close();
						return 1;
					}
				}
				
			}
			
			br.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return 2;
	}	

	public static void setNewPassword(String id, String password){
		
		
		try{
			
			File file = new File("data/admin.txt");
			FileWriter fw =  new FileWriter(file);
			fw.write("");
			fw.close();
			
			FileWriter writer = new FileWriter("data/admin.txt");
			writer.write(id+" "+password);
			writer.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
}
