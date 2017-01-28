package be.howest.twentytwo.parametergame.model.event.pickup;

import com.badlogic.ashley.core.Entity;

public abstract class BasePickupCallback {

	public abstract void handle(Entity entity);
	
}
