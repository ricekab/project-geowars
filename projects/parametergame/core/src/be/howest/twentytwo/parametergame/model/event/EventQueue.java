package be.howest.twentytwo.parametergame.model.event;

import java.util.Map;
import java.util.Set;

import com.badlogic.ashley.core.ComponentMapper;

import be.howest.twentytwo.parametergame.model.event.callback.IEventCallback;
import be.howest.twentytwo.parametergame.model.event.type.IEventData;


/**
 * Manages all game events that are generated by systems to be distributed to any relevant
 * observers.
 */
public class EventQueue {
	
	private Map<IEventData, Set<IEventCallback<IEventData>>> observerMap;
	
	public <T extends IEventData> void getObserversFor(T eventType){
		
	}
	
	public void dispatch(IEventData data){
	}
}
