package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

import com.badlogic.ashley.core.Entity;

public class DestroyEntityEvent implements IEvent {
	
	private final Entity entity;
	
	public DestroyEntityEvent(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity(){
		return entity;
	}
	
	@Override
	public EventEnum getType() {
		return EventEnum.DESTROY_ENTITY;
	}

}
