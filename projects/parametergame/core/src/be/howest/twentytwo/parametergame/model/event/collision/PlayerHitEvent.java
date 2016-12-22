package be.howest.twentytwo.parametergame.model.event.collision;

import com.badlogic.gdx.physics.box2d.Fixture;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

public class PlayerHitEvent extends BaseCollisionEvent {
	
	public PlayerHitEvent(Fixture subject, Fixture collider) {
		super(subject, collider);
	}

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_HIT;
	}

}
