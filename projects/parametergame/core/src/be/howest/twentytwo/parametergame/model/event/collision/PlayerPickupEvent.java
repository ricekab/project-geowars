package be.howest.twentytwo.parametergame.model.event.collision;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PlayerPickupEvent extends BaseCollisionEvent{

	public PlayerPickupEvent(Fixture subject, Fixture collider) {
		super(subject, collider);
	}

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_PICKUP;
	}

	/** Aliases to retrieve collision subject. */
	public Entity getPlayerEntity() {
		return super.getSubject();
	}
	
	public Fixture getPlayerFixture(){
		return super.getSubjectFixture();
	}
	
	public Entity getPickupEntity(){
		return super.getCollider();
	}
	
	public Fixture getPickupFixture(){
		return super.getColliderFixture();
	}

	
}
