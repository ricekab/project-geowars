package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.WeaponFiredEvent;

public abstract class BaseWeaponFiredHandler implements IEventListener {
	
	@Override
	public void handle(IEvent event) {
		if (event != null && event instanceof WeaponFiredEvent) {
			handleEvent((WeaponFiredEvent) event);
		}
	}

	public abstract void handleEvent(WeaponFiredEvent event);

}
