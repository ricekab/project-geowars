package be.howest.twentytwo.parametergame.dataTypes;

public interface GameIdDataI {
	
	public void setMode(String mode);
	
	/**
	 * Generates a BigInteger, and converts it to string, then sets the difficultyId with that value
	 */
	public void setUniqueId();
	
	public void setDifficultyId(String difficultyId);
	
	public String getMode();
	public String getUniqueId();
	public String getDifficultyId();
	
}
