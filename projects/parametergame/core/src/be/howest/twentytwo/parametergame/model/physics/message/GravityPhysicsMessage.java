package be.howest.twentytwo.parametergame.model.physics.message;

import be.howest.twentytwo.parametergame.dataTypes.PlanetDataI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Physics Event describing a body being attracted towards another body.
 */
public class GravityPhysicsMessage extends RepeatingPhysicsMessage {

	// TODO: This might be overkill? Real life value is quite small and will be
	// inaccurate as a float.
	// Real life value = 6.674E-11
	public static final float GRAVITATIONAL_CONSTANT = 5f; // 6.674E-3f

	private PlanetDataI planetData;
	private Body sourceBody;
	private Body targetBody;

	public GravityPhysicsMessage(Fixture source, Body target) {
		super();
		// PlanetData in gravity fixture
		this.planetData = (PlanetDataI) source.getUserData();
		this.sourceBody = source.getBody();
		this.targetBody = target;
	}

	@Override
	public void execute() {
		float distanceSquared = targetBody.getPosition().dst2(sourceBody.getPosition());
		Vector2 gravityVector = sourceBody.getPosition().sub(targetBody.getPosition());

		/* Turns out static bodies have 0 mass. So simulate it (pass by data) */
		gravityVector.scl(GRAVITATIONAL_CONSTANT).scl(1f / distanceSquared);
		gravityVector.scl(planetData.getMass() * targetBody.getMass()); // Simulate planet masss
		// Fg = m(planet) * G(constant) / (r*r)
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
		if(obj != null && obj instanceof GravityPhysicsMessage) {
			GravityPhysicsMessage other = (GravityPhysicsMessage) obj;
			if(getSourceBody().equals(other.getSourceBody())
					&& getTargetBody().equals(other.getTargetBody())) {
				return true;
			}
		}
		return false;
	}

}
