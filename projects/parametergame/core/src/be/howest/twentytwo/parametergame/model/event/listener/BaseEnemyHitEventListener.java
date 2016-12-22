package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.EnemyHitEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;

public abstract class BaseEnemyHitEventListener implements IEventListener {

	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof EnemyHitEvent) {
			handleEvent((EnemyHitEvent) event);
		}
	}

	public abstract void handleEvent(EnemyHitEvent event);

}
