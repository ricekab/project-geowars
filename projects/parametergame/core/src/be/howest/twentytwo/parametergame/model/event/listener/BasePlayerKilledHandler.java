package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;

public abstract class BasePlayerKilledHandler implements IEventListener {

	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof PlayerKilledEvent) {
			handleEvent((PlayerKilledEvent) event);
		}
	}

	public abstract void handleEvent(PlayerKilledEvent event);

}
