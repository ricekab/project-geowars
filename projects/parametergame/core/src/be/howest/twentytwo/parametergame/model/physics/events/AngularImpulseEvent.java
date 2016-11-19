package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.physics.box2d.Body;

public class AngularImpulseEvent extends SinglePhysicsEvent {

	private Body body;
	private float impulse;

	// TODO: Data passed as param or smth? (See GravityPhysicsEvent)
	public AngularImpulseEvent(Body body, float impulse) {
		super();
		this.body = body;
		this.impulse = impulse;
	}

	@Override
	public void execute() {
		super.execute();
		body.applyAngularImpulse(impulse, true);
	}
	
	public Body getBody(){
		return body;
	}
	
	public float getImpulse(){
		return impulse;
	}
	
	@Override
	public int hashCode() {
		int prime = 41;
		return getBody().hashCode() * Math.round(getImpulse()) * prime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof AngularImpulseEvent) {
			AngularImpulseEvent other = (AngularImpulseEvent) obj;
			if(getBody().equals(other.getBody()) && getImpulse() == other.getImpulse()) {
				return true;
			}
		}
		return false;
	}
}
