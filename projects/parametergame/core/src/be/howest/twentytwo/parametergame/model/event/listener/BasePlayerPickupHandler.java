package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerPickupEvent;

public abstract class BasePlayerPickupHandler implements IEventListener{
	
	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof PlayerPickupEvent) {
			handleEvent((PlayerPickupEvent) event);
		}
	}

	public abstract void handleEvent(PlayerPickupEvent event);
}
