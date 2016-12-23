package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.GameEndEvent;

public abstract class BaseGameEndHandler implements IEventListener {
	
	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof GameEndEvent) {
			handleEvent((GameEndEvent) event);
		}
	}

	public abstract void handleEvent(GameEndEvent event);
}
