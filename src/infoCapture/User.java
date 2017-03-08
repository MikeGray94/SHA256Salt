package infoCapture;

public class User {
	
	private int userID;
	private String username;
	private String hashcode;
	private String salt;
	
	public User(String uName, String hashPass, int uID, String salt) {
		this.setUsername(uName);
		this.setHashcode(hashPass);
		this.setUserID(uID);
		this.setSalt(salt);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


}
