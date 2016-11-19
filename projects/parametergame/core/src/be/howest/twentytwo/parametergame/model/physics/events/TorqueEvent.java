package be.howest.twentytwo.parametergame.model.physics.events;

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
	
	@Override
	public Body getSourceBody(){
		return unit;
	}
	
	public float getForce(){
		return force;
	}
	
	@Override
	public int hashCode() {
		int prime = 41;
		return getSourceBody().hashCode() * Math.round(getForce()) * prime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TorqueEvent) {
			TorqueEvent other = (TorqueEvent) obj;
			if(getSourceBody().equals(other.getSourceBody()) && getForce() == other.getForce()) {
				return true;
			}
		}
		return false;
	}
}
