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
	
	public Body getUnit(){
		return unit;
	}
	
	public float getForce(){
		return force;
	}
	
	@Override
	public int hashCode() {
		// TODO: This hashcode is bad, doesn't account for isConsumed either.
		return (int)(getUnit().hashCode() * getForce());	// TODO Float to int => safe cast?
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof LinearForceEvent){
			LinearForceEvent other = (LinearForceEvent)obj;
			// TODO: Comparing floats by equality. This is not safe.
			if(getUnit().equals(other.getUnit()) && getForce() == other.getForce()){
				return true;
			}
		}
		return false;
	}
}
