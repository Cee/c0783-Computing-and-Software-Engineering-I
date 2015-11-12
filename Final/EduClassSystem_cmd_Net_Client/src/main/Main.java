package main;

import java.util.Scanner;

import net.Client;

public class Main {

	public static void main(String[] args){
		Client client = new Client();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);		
		while (true){
			String line = scanner.nextLine();
			client.sendMessage(line);
		}
	}
}
