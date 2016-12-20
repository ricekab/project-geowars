package be.howest.twentytwo.parametergame.model.event;

/**
 * Represents a filter to sift through a collection of events (of a particular type).
 */
public interface IEventFilter<T extends IEvent> {

	/**
	 * Return true if it passes the filter, false otherwise.
	 */
	public boolean filter(T message);
	
}
