package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;

import com.badlogic.gdx.physics.box2d.Body;

public class DampenToggleAction implements InputAction {

	private final Body body;
	private final MovementComponent movement;
	
	public DampenToggleAction(BodyComponent playerBody, MovementComponent playerMovement) {
		this.body = playerBody.getBody();
		this.movement = playerMovement;
	}
	
	@Override
	public void start() {
		movement.toggleDampen();
		if(movement.isDampenOn()){
			body.setLinearDamping(movement.getLinearDampStrength());
		} else{
			body.setLinearDamping(0f);
		}
	}

	@Override
	public void stop() {
		return;
	}

}
