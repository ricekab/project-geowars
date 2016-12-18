package be.howest.twentytwo.parametergame.model.physics.message;

import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Physics Event describing a body being attracted towards another body.
 */
public class GravityPhysicsMessage extends RepeatingPhysicsMessage {

	// TODO: This might be overkill? Real life value is quite small and will be
	// inaccurate as a float.
	// Real life value = 6.674E-11
	public static final float GRAVITATIONAL_CONSTANT = 2f; // 6.674E-3f

	private Body sourceBody;
	private Body targetBody;
	// private IAttractable attractable;

	public GravityPhysicsMessage(Body source, Body target/* , IAttractable attr */) {
		super();
		this.sourceBody = source;
		this.targetBody = target;
		// this.attractable = attr;
	}

	@Override
	public void execute() {
		float distanceSquared = targetBody.getPosition().dst2(sourceBody.getPosition());
		Vector2 gravityVector = sourceBody.getPosition().sub(targetBody.getPosition());

		/* Turns out static bodies have 0 mass. So simulate it (pass by data) */
		gravityVector.scl(GRAVITATIONAL_CONSTANT).scl(1f / distanceSquared);
		gravityVector.scl(1000f); // Simulate planet mass
		// Fg = m(planet) * G(constant) / (r*r)

		Gdx.app.debug("GravityPhysxEvt", gravityVector.toString());
		Gdx.app.debug("GravityPhysxEvt", "Scaled: " + new Vector2(gravityVector).scl(PhysicsSystem.PHYSICS_TIMESTEP).toString());
		targetBody.applyForceToCenter(gravityVector, true);
	}

	public Body getSourceBody() {
		return sourceBody;
	}

	public Body getTargetBody() {
		return targetBody;
	}

	@Override
	public int hashCode() {
		int prime = 37;
		return getSourceBody().hashCode() * getTargetBody().hashCode() * prime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof GravityPhysicsMessage) {
			GravityPhysicsMessage other = (GravityPhysicsMessage) obj;
			if (getSourceBody().equals(other.getSourceBody()) && getTargetBody().equals(other.getTargetBody())) {
				return true;
			}
		}
		return false;
	}

}
