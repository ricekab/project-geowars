package be.howest.twentytwo.parametergame.dataTypes;


public class DefaultDifficultyData implements DifficultyDataI {

	@Override
	public String getID() {
		return "PLAYER_DIFFICULTY";
	}

	@Override
	public float getHealthModifier() {
		return 1;
	}

	@Override
	public float getMovementModifier() {
		return 1;
	}

	@Override
	public float getFirerateModifier() {
		return 1;
	}

	@Override
	public float getScoreModifier() {
		return 1;
	}

}
