package be.howest.twentytwo.parametergame.dataTypes;

public class DifficultyData implements DifficultyDataI{

	private String id;
	private float healthModifier;
	private float movementModifier;
	private float firerateModifier;
	private float scoreModifier;
	
	public DifficultyData(String id, float healthModifier, float movementModifier, float firerateModifier, float scoreModifier) {
		this.id = id;
		this.healthModifier = healthModifier;
		this.movementModifier = movementModifier;
		this.firerateModifier = firerateModifier;
		this.scoreModifier = scoreModifier;
	}
	
	//	GETTERS
	
	@Override
	public String getID() {
		return id;
	}

	@Override
	public float getHealthModifier() {
		return healthModifier;
	}

	@Override
	public float getMovementModifier() {
		return movementModifier;
	}

	@Override
	public float getFirerateModifier() {
		return firerateModifier;
	}

	@Override
	public float getScoreModifier() {
		return scoreModifier;
	}

}
