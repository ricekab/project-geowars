package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;

public abstract class BasePlayerHitHandler implements IEventListener {


	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof PlayerHitEvent) {
			handleEvent((PlayerHitEvent) event);
		}
	}

	public abstract void handleEvent(PlayerHitEvent event);

}
