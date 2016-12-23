package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;

public abstract class BaseEnemyKilledHandler implements IEventListener {

	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof EnemyKilledEvent) {
			handleEvent((EnemyKilledEvent) event);
		}
	}

	public abstract void handleEvent(EnemyKilledEvent event);

}
