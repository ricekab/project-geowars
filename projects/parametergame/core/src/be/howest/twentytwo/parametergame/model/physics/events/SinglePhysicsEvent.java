package be.howest.twentytwo.parametergame.model.physics.events;

/**
 * Represents an event that only fires once.
 */
public abstract class SinglePhysicsEvent implements IPhysicsEvent {
	
	private boolean isConsumed;
	
	public SinglePhysicsEvent() {
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
