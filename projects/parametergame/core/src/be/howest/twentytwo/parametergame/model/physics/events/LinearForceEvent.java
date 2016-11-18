package be.howest.twentytwo.parametergame.model.physics.events;

import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Describes a force event that is applied on the target body's center of mass.
 */
public class LinearForceEvent extends SinglePhysicsEvent {

	private Body body;
	private Vector2 forceVector;
	private float force;

	/**
	 * Creates a single physics event that applies the given force vector on the body's center of
	 * mass.
	 */
	public LinearForceEvent(Body body, Vector2 forceVector) {
		super();
		this.body = body;
		this.forceVector = forceVector;
	}

	/**
	 * Shortcut constructor that allows this event to be created with a specified amount of force.
	 * This force is applied in the direction the body is facing (forward).
	 */
	public LinearForceEvent(Body body, float force) {
		this(body, VectorMath.forceToVector(force, body.getAngle()));
	}

	@Override
	public void execute() {
		super.execute();
		body.applyForceToCenter(forceVector, true);
	}

	public Body getUnit() {
		return body;
	}

	public float getForce() {
		return force;
	}

	@Override
	public int hashCode() {
		// TODO: This hashcode is bad, doesn't account for isConsumed either.
		return (int) (getUnit().hashCode() * getForce()); // TODO Float to int => safe cast?
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof LinearForceEvent) {
			LinearForceEvent other = (LinearForceEvent) obj;
			// TODO: Comparing floats by equality. This is not safe.
			if(getUnit().equals(other.getUnit()) && getForce() == other.getForce()) {
				return true;
			}
		}
		return false;
	}
}
