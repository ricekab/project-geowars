package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.EnemyHitEvent;

public abstract class BaseEnemyHitHandler implements IEventListener {

	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof EnemyHitEvent) {
			handleEvent((EnemyHitEvent) event);
		}
	}

	public abstract void handleEvent(EnemyHitEvent event);

}
