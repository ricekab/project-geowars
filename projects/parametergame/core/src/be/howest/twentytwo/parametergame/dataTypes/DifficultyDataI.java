package be.howest.twentytwo.parametergame.dataTypes;

public interface DifficultyDataI {

	public String getID();
	
	public float getHealthModifier();
	public float getMovementModifier();
	public float getFirerateModifier();
	
	public float getScoreModifier();
}
