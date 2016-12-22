package be.howest.twentytwo.parametergame.model.event.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;

import be.howest.twentytwo.parametergame.model.event.IEvent;

public abstract class BaseCollisionEvent implements IEvent {

	private final Fixture subjectFixture, colliderFixture;
	private final Entity subject, collider;

	public BaseCollisionEvent(Fixture subject, Fixture collider) {
		this.subjectFixture = subject;
		this.subject = (Entity) subject.getBody().getUserData();
		this.colliderFixture = collider;
		this.collider = (Entity) collider.getBody().getUserData();
	}

	/**
	 * The subject of the event. For example, a {@link PlayerHitEvent} will have
	 * the player as the subject and the entity that resulted in the hit as it's
	 * collider.
	 */
	public Fixture getSubjectFixture() {
		return subjectFixture;
	}

	public Fixture getColliderFixture() {
		return colliderFixture;
	}

	public Entity getSubject() {
		return subject;
	}

	public Entity getCollider() {
		return collider;
	}
}
