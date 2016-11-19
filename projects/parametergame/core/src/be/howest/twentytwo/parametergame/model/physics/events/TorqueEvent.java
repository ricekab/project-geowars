package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.physics.box2d.Body;

public class TorqueEvent extends SinglePhysicsEvent {

	private Body body;
	private float force;

	// TODO: Data passed as param or smth? (See GravityPhysicsEvent)
	public TorqueEvent(Body body, float force) {
		super();
		this.body = body;
		this.force = force;
	}

	@Override
	public void execute() {
		body.applyTorque(force ,true);
		super.execute();
	}
	
	public Body getBody(){
		return body;
	}
	
	public float getForce(){
		return force;
	}
	
	@Override
	public int hashCode() {
		int prime = 41;
		return getBody().hashCode() * Math.round(getForce()) * prime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TorqueEvent) {
			TorqueEvent other = (TorqueEvent) obj;
			if(getBody().equals(other.getBody()) && getForce() == other.getForce()) {
				return true;
			}
		}
		return false;
	}
}
