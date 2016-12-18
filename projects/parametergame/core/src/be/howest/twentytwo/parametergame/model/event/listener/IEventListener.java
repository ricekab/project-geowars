package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;

public interface IEventListener<T extends IEvent> {
	
	public void handle(T event);

}
