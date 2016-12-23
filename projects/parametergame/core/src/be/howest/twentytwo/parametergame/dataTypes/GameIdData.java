package be.howest.twentytwo.parametergame.dataTypes;

import java.security.SecureRandom;
import java.math.BigInteger;

public class GameIdData implements GameIdDataI{

	private String mode;
	private String uniqueId;
	private String difficultyId;
	private final SecureRandom random = new SecureRandom();
	
	public GameIdData(String mode, String difficultyId) {
		setMode(mode);
		setUniqueId();
		setDifficultyId(difficultyId);
	}
	
	//	SETTERS

	@Override
	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public void setUniqueId() {
		uniqueId = new BigInteger(130, random).toString(32);
	}

	@Override
	public void setDifficultyId(String difficultyId) {
		this.difficultyId = difficultyId;
	}
	
	//	GETTERS

	@Override
	public String getMode() {
		return mode;
	}

	@Override
	public String getUniqueId() {
		return uniqueId;
	}

	@Override
	public String getDifficultyId() {
		return difficultyId;
	}

}
