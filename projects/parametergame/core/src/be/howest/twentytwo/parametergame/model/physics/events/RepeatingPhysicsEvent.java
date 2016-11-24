package be.howest.twentytwo.parametergame.model.physics.events;

/**
 * Represents an event that repeats until explicitly told to stop using {@link #setConsumed()}.
 */
public abstract class RepeatingPhysicsEvent implements IPhysicsEvent {

	private boolean isConsumed;
	
	public RepeatingPhysicsEvent() {
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
