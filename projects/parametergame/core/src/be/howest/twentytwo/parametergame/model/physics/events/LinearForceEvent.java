package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
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
		super.execute();
		Vector2 forceVector = new Vector2(force * MathUtils.cos(unit.getAngle() + MathUtils.PI/2), force
				* MathUtils.sin(unit.getAngle() + MathUtils.PI/2));
		Gdx.app.log("LFE", "angle: " + unit.getAngle());
		Gdx.app.log("LinearForceEvent", "F: " + forceVector.toString());
		unit.applyForceToCenter(forceVector, true);
	}
}
