package be.howest.twentytwo.parametergame.input.actions;

/**
 * An input action that is will toggle an action to be active or not (on / off).
 */
public interface InputAction {

	// TODO: Method names can be confusing.

	/**
	 * Called when action has to be performed.
	 */
	public void start();

	/**
	 * Called when action has to be stopped.
	 */
	public void stop();
}
