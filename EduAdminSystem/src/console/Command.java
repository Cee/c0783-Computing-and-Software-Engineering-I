package console;

import java.util.Scanner;

public class Command {
	
	Scanner inputCmd;
	String command;
	
	public static void main(String[] args){
		Command commandUI= new Command();
		commandUI.go();
    }
	
	public void go(){
		while (true){
			System.out.println("-------------Login/Register-------------");
			getCmd();
			checkLogin(command);
		}
	}
	
	 public void getCmd() {
		inputCmd = new Scanner(System.in);
		command = inputCmd.nextLine();
	 }
	 
	 public static void checkLogin(String input){	
						
			String[] split = input.split(" ");	
		
			if (split.length != 3){
				System.out.println("-------------Wrong Input-------------");
			}
			else{
				if(split[0].toLowerCase().equals("login")){
					String id = split[1];
					String password = split[2];
					String group = Io.checkGroup(id);
					int status = Io.checkData(id,password,group);
					switch (status) {
					case 0:		
						Instructions.go(group);
						break;
					case 1:
						System.out.println("-------------Wrong  Password-------------");
						break;
					default:
						System.out.println("----------------Not Exist----------------");
						break;
					}
					
					
				}
				else{
					System.out.println("-------------Wrong Input-------------");
				}
			}

			
			
			
	
	}
}
