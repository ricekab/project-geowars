package be.howest.twentytwo.parametergame.model.event;

/**
 * A message pertaining some game event for other systems to process.
 * 
 */
public interface IEvent {

	public EventEnum getType();
	
	
	
}
