package be.howest.twentytwo.parametergame.model.event.collision;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

import com.badlogic.gdx.physics.box2d.Fixture;

public class PlanetCrashEvent extends BaseCollisionEvent {

	public PlanetCrashEvent(Fixture subject, Fixture collider) {
		super(subject, collider);
	}

	@Override
	public EventEnum getType() {
		return EventEnum.PLANET_CRASH;
	}

}
