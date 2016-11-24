package be.howest.twentytwo.parametergame.input.actions;

public interface KeyInputAction {

	// TODO: Method names can be confusing.

	/**
	 * Called on key down
	 */
	public void start();

	/**
	 * Called on key up.
	 */
	public void stop();
}
