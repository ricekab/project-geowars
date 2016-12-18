package be.howest.twentytwo.parametergame.model.physics.message;

/**
 * Represents an event that only fires once.
 */
public abstract class SinglePhysicsMessage implements IPhysicsMessage {
	
	private boolean isConsumed;
	
	public SinglePhysicsMessage() {
		this.isConsumed = false;
	}

	@Override
	public void execute() {
		isConsumed = true;
	}

	@Override
	public boolean isConsumed() {
		return isConsumed;
	}
	
	@Override
	public boolean setConsumed() {
		isConsumed = true;
		return true;
	}

}
