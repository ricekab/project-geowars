package be.howest.twentytwo.parametergame.input.actions;

public interface InputAction {

	// TODO: Method names can be confusing.

	/**
	 * Called on key/touch down
	 */
	public void start();

	/**
	 * Called on key/touch up.
	 */
	public void stop();
}
