package infoCapture;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoginSystem {
	String uName = null;
	Scanner s = new Scanner(System.in);
	int hashRounds = 200_000;
	
	private ArrayList<User> userList = new ArrayList<User>();
	
	public void login(){
		String uName = null;
		String hashedPass;
		int userID = 0;

		System.out.println("Please enter your username:");
		uName = s.next();
		
		for(User u : userList){
			if(u.getUsername().equals(uName)){
				userID = u.getUserID();
			}
		}
		
		System.out.println("Please enter your password:");
		String tempSalt = userList.get(userID-1).getSalt();
		hashedPass = hashSHA(s.next(), tempSalt);
		
		for(User u : userList){
			if((u.getUsername().equals(uName)) & (u.getHashcode().equals(hashedPass))){
				System.out.println("Login successful! Welcome, " + uName);
			}
			else{
				System.out.println("Username or password incorrect.");
			}
		}
	}
	
	public void register(){
		
		String hashFirst = null;
		String hashSecond;
		boolean uNameInput = false;
		boolean passMatch = false;
		String tempSalt = saltGen();

		System.out.println("Please enter your desired username:");
		while(!uNameInput){
			uName = s.next();
			if(userList.size() > 0){
				for(User u: userList){
					if(u.getUsername().equals(uName)){
						System.out.println("Username already taken! Please try again:");
					}
					else{
						uNameInput = true;
						break;
					}
				}
			}
			else{
				uNameInput = true;
			}
		}
		
		while(!passMatch){
			System.out.println("Please enter your password:");
			hashFirst = hashSHA(s.next(), tempSalt);
			System.out.println("Please re-enter your password:");
			hashSecond = hashSHA(s.next(), tempSalt);
			if(!(hashFirst.equals(hashSecond))){
				System.out.println("Passwords do not match, please try again!");
				continue;
			}
			else{
				System.out.println("Account created successfully! Welcome, " + uName + "!");
				passMatch = true;
				break;
			}
		}
		
		int tempSize = getSize();
		User tempUser = new User(uName, hashFirst, tempSize + 1, tempSalt);
		userList.add(tempUser);
	}
	
	public String hashSHA(String _pass, String _salt){
		String hash = "";
		StringBuilder sb = new StringBuilder();
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		for(int j = 0; j < hashRounds; j++){
			md.update(hash.concat(_pass).concat(_salt).getBytes());
			byte[] byteData = md.digest();
			
			for(int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
			sb.setLength(0);
		}

		return hash;
	}
	
	public String saltGen(){
		Random rnd = new Random();
		StringBuilder hexTemp = new StringBuilder();
		for(int i = 0; i < 32; i++){
			hexTemp.append(Integer.toHexString(rnd.nextInt(16)));
		}
		return hexTemp.toString();
	}
	
	public int getSize(){
		return userList.size();
	}
	
	public void printList(){			//Mainly only for debugging purposes, would not be callable by general user
		for(User u : userList){
			System.out.println("Username: " + u.getUsername() + " | User ID: " + u.getUserID() + " | Hashcode: " + u.getHashcode() + " | Salt: " + u.getSalt());
		}
	}
	
	private LoginSystem(){}
	
	private static class SystemHolder{
		private static final LoginSystem INSTANCE = new LoginSystem();
	}
	
	public static LoginSystem getInstance(){
		return SystemHolder.INSTANCE;
	}
}
