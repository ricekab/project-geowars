package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class TorqueEvent extends SinglePhysicsEvent {

	private Body unit;
	private float force;

	// TODO: Data passed as param or smth? (See GravityPhysicsEvent)
	public TorqueEvent(Body unit, float force) {
		super();
		this.unit = unit;
		this.force = force;
	}

	@Override
	public void execute() {
		unit.applyTorque(force ,true);
		super.execute();
	}
}
