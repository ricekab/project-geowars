package be.howest.twentytwo.parametergame.model.event.collision;

import com.badlogic.gdx.physics.box2d.Fixture;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class PlayerPickupEvent extends BaseCollisionEvent{

	public PlayerPickupEvent(Fixture subject, Fixture collider) {
		super(subject, collider);
	}

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_PICKUP;
	}

}
