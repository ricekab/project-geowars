package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class LinearForceEvent extends SinglePhysicsEvent {

	private Body unit;
	private float force;

	public LinearForceEvent(Body unit, float force) {
		super();
		this.unit = unit;
		this.force = force;
	}

	@Override
	public void execute() {
		unit.applyForceToCenter(
				new Vector2((float) (force * Math.sin(unit.getAngle())), (float) (force * Math.cos(unit.getAngle()))),
				true);
		super.execute();
	}

}
