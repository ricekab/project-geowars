package be.howest.twentytwo.parametergame.model.physics.events;

/**
 * Describes an event between 1 or more bodies in the physics world.
 */
public interface IPhysicsEvent {

	/**
	 * Perform the event described event. Typically this means applying a force on a body based on some parameters
	 * supplied at construction.
	 */
	public void execute();

	/**
	 * Must return true when this event had been consumed and should no longer be executed. Return false when this event
	 * is still active.
	 */
	public boolean isConsumed();

	/**
	 * Attempt to set the event to a consumed state. The event in question may reject to go into a consumed state. In
	 * that case false is returned, true otherwise.
	 */
	public boolean setConsumed();
}
