package be.howest.twentytwo.parametergame.model.event;

/**
 * A message pertaining some game event for other systems to process.
 * 
 */
public interface IEvent {

	public int getID();
	
	/**
	 * Must return true when this event had been consumed and should no longer be executed. Return false when this event
	 * is still active.
	 */
	public boolean isConsumed();

	/**
	 * Attempt to set the event to a consumed state. The event in question may reject to go into a consumed state. In
	 * that case false is returned, true otherwise. If the event is already consumed, this return true.
	 */
	public boolean setConsumed();
	
	
}
