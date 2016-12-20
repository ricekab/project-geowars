package be.howest.twentytwo.parametergame.model.event.game;

import com.badlogic.ashley.core.Entity;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

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
