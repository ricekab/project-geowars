package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.physics.events.RepeatingPhysicsEvent;

/**
 * Physics Event describing a body being attracted towards another body.
 */
public class GravityPhysicsEvent extends RepeatingPhysicsEvent {
	
	// TODO: This might be overkill? Real life value is quite small and will be inaccurate as a float.
	// Real life value = 6.674E-11
	public static final float GRAVITATIONAL_CONSTANT = 1f;	// 6.674E-3f
	
	private Body sourceBody;
	private Body targetBody;
	// private IAttractable attractable;
	
	
	public GravityPhysicsEvent(Body source, Body target/*, IAttractable attr*/) {
		super();
		this.sourceBody = source;
		this.targetBody = target;
		// this.attractable = attr;
	}

	@Override
	public void execute() {
		float distance = targetBody.getPosition().dst(sourceBody.getPosition());
		Vector2 gravityVector = sourceBody.getPosition().sub(targetBody.getPosition());
		
		/*Turns out static bodies have 0 mass. So simulate it (pass by data) */
		gravityVector.scl(GRAVITATIONAL_CONSTANT).scl(1f/distance*2f);
		gravityVector.scl(5f);		// Simulate planet mass
		// Fg = m(planet) * G(constant) / (r*r)
		
		Gdx.app.log("GravityPhysxEvt", gravityVector.toString());
		
		targetBody.applyForceToCenter(gravityVector, true);
	}

}
