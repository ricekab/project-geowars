package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.GameLoseEvent;

public abstract class BaseGameEndHandler implements IEventListener {
	
	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof GameLoseEvent) {
			handleEvent((GameLoseEvent) event);
		}
	}

	public abstract void handleEvent(GameLoseEvent event);
}
