package be.howest.twentytwo.parametergame.model.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages all game events that are generated by systems to be distributed to any relevant
 * observers.
 */
public class EventQueue {

	// EXAMPLE OBSERVER REGISTRATION
	// register(EventEnum.PLAYER_HIT, new IEventListener() {
	//
	// @Override
	// public void handle(IEvent event) {
	// // TODO: Going to have to cast for every callback object.
	// // Either this or need to create specific observables for everything which makes
	// // extension a problem...
	// PlayerHitEvent phEvt = (PlayerHitEvent) event;
	// }
	// });
	
	private final Collection<IEvent> events;
	private final Map<EventEnum, Collection<IEventListener>> eventListeners;

	public EventQueue() {
		this.events = new ArrayList<IEvent>();
		this.eventListeners = new HashMap<EventEnum, Collection<IEventListener>>();
	}

	/**
	 * Parse out all queued messages.
	 */
	public void dispatch() {
		for (IEvent evt : events) {
			Collection<IEventListener> listeners = eventListeners.get(evt.getType());
			if(listeners != null) {
				for (IEventListener cb : listeners) {
					cb.handle(evt);
				}
			}
		}
		events.clear();
	}

	/**
	 * Adds event to the queue.
	 */
	public void send(IEvent event) {
		events.add(event);
	}

	/**
	 * Alias for {@link #send(IEvent)}
	 */
	public void addEvent(IEvent event) {
		send(event);
	}

	/**
	 * Register a callback for a certain event
	 */
	public void register(EventEnum eventID, IEventListener callback) {
		Collection<IEventListener> listeners = eventListeners.get(eventID);
		if(listeners != null) {
			listeners.add(callback);
		} else {
			Collection<IEventListener> eventList = new ArrayList<IEventListener>();
			eventList.add(callback);
			eventListeners.put(eventID, eventList);
		}
	}

}
