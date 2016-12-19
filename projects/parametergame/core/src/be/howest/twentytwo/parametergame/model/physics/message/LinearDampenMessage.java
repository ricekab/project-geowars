package be.howest.twentytwo.parametergame.model.physics.message;

import com.badlogic.gdx.physics.box2d.Body;

public class LinearDampenMessage extends SinglePhysicsMessage {

	private final Body body;
	private final float dampStrength;
	
	public LinearDampenMessage(Body body, float linearDampStrength) {
		this.body = body;
		this.dampStrength = linearDampStrength;
	}
	
	@Override
	public void execute() {
		super.execute();
		body.setLinearDamping(dampStrength);
	}

}
