package be.howest.twentytwo.parametergame.dataTypes;

public class UserData implements UserDataI{
	
	private String id;
	private String user;
	private String passwordHash;
	
	public UserData(String user, String passwordHash) {
		this.id = id;
		this.user = user;
		this.passwordHash = passwordHash;
	}
	
	//	GETTERS
	
	public String getID() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getPasswordHashed() {
		return passwordHash;
	}

}
