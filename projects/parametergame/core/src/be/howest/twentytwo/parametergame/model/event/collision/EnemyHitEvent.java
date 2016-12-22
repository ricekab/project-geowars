package be.howest.twentytwo.parametergame.model.event.collision;

import com.badlogic.gdx.physics.box2d.Fixture;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class EnemyHitEvent extends BaseCollisionEvent {

	public EnemyHitEvent(Fixture subject, Fixture collider) {
		super(subject, collider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_HIT;
	}

}
