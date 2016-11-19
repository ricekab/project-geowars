package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.utils.VectorMath;

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

	@Override
	public Body getSourceBody() {
		return body;
	}

	public float getForce() {
		return force;
	}
	
	public Vector2 getForceVector(){
		return forceVector;
	}

	@Override
	public int hashCode() {
		return getSourceBody().hashCode() * getForceVector().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof LinearForceEvent) {
			LinearForceEvent other = (LinearForceEvent) obj;
			if(getSourceBody().equals(other.getSourceBody()) && getForceVector() == other.getForceVector()) {
				return true;
			}
		}
		return false;
	}
}
