package be.howest.twentytwo.parametergame.model.physics.message;

import com.badlogic.gdx.math.Vector2;

public class CreateBodyMessage extends SinglePhysicsMessage {

	private Vector2 spawnPosition;
	private float spawnRotation;
	
	private Vector2 initialLinearVel;
	private float initialAngularVel;
	
	public CreateBodyMessage() {
		// TODO Auto-generated constructor stub
	}
}
