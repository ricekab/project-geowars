package be.howest.twentytwo.parametergame.dataTypes;

public class UserData implements UserDataI{
	
	private String id;
	private String user;
	private String passwordHash;
	private String difficulty;
	
	public UserData(String user, String passwordHash, String difficulty) {
		this.user = user;
		this.passwordHash = passwordHash;
		setDifficulty(difficulty);		// TODO: @Nick -- Difficulty tied to user??
	}
	
	@Deprecated
	public UserData(String user, String passwordHash) {
		this.user = user;
		this.passwordHash = passwordHash;
	}
	
	//	GETTERS
	
	@Deprecated
	public String getID() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getPasswordHashed() {
		return passwordHash;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	//	SETTERS
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
