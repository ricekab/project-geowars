package be.howest.twentytwo.parametergame.model.physics.message;

/**
 * Represents an event that repeats until explicitly told to stop using {@link #setConsumed()}.
 */
public abstract class RepeatingPhysicsMessage implements IPhysicsMessage {

	private boolean isConsumed;
	
	public RepeatingPhysicsMessage() {
		this.isConsumed = false;
	}

	@Override
	public boolean isConsumed() {
		return isConsumed;
	}

	@Override
	public boolean setConsumed() {
		this.isConsumed = true;
		return true;
	}

}
