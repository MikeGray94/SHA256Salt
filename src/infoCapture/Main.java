package infoCapture;

import java.util.Scanner;

public class Main {
	
	private enum Action{
		LOGIN, REGISTER, VIEW, EXIT
	}
	
	public static void main(String[] args) {
		Scanner sm = new Scanner(System.in);
		run(sm);
	}
	
	private static void run(Scanner sm){

		LoginSystem ls = LoginSystem.getInstance();
		
		boolean done = false;
		
		while(!done){
			System.out.println("Select an action: login | register | view | exit");
			String toDo = sm.next();						//Capture input
			Action a = null;								//Initialise enum
			try{
				a = Action.valueOf(toDo.toUpperCase());		//Converts input to enum
			}
			catch(IllegalArgumentException e){
				System.out.println("Invalid action, please try again.");
				continue;
			}
			
			switch(a){
			
			case LOGIN:
				ls.login();
				break;
			
			case REGISTER:
				ls.register();
				break;
			
			case VIEW:
				ls.printList();
				break;
				
			case EXIT:
				done = true;
				break;
				
			default:
				System.out.println("Invalid input, please try again.");
			}
		}
	}
	
	
}
