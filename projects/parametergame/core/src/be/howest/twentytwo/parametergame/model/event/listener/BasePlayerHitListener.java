package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;

public abstract class BasePlayerHitListener implements IEventListener {


	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof PlayerHitEvent) {
			handleEvent((PlayerHitEvent) event);
		}
	}

	public abstract void handleEvent(PlayerHitEvent event);

}
