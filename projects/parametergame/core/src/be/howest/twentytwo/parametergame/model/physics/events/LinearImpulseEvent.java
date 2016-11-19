package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.utils.VectorMath;

/**
 * Describes a force event that is applied on the target body's center of mass.
 */
public class LinearImpulseEvent extends SinglePhysicsEvent {

	private Body body;
	private Vector2 impulseVector;

	/**
	 * Creates a single physics event that applies the given impulse vector on
	 * the body's center of mass.
	 */
	public LinearImpulseEvent(Body body, Vector2 impulseVector) {
		super();
		this.body = body;
		this.impulseVector = impulseVector;
	}

	/**
	 * Shortcut constructor that allows this event to be created with a
	 * specified amount of impulse. This impulse is applied in the direction the
	 * body is facing (forward).
	 */
	public LinearImpulseEvent(Body body, float force) {
		this(body, VectorMath.forceToForwardVector(force, body.getAngle()));
	}

	@Override
	public void execute() {
		super.execute();
		// body.applyForceToCenter(forceVector, true); // Switched to impulse
		body.applyLinearImpulse(impulseVector, body.getWorldCenter(), true);
	}

	public Body getBody() {
		return body;
	}

	public Vector2 getImpulseVector() {
		return impulseVector;
	}

	@Override
	public int hashCode() {
		return getBody().hashCode() * getImpulseVector().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof LinearImpulseEvent) {
			LinearImpulseEvent other = (LinearImpulseEvent) obj;
			if (getBody().equals(other.getBody()) && getImpulseVector() == other.getImpulseVector()) {
				return true;
			}
		}
		return false;
	}
}
