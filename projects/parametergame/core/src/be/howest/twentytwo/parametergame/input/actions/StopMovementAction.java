package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class StopMovementAction implements InputAction {
	
	private MovementComponent moveComponent;

	public StopMovementAction(MovementComponent mc) {
		this.moveComponent = mc;
	}

	
	@Override
	public void start() {
		moveComponent.setAccelerateForward(false);
		moveComponent.setAccelerateBackward(false);
		moveComponent.setTurnLeft(false);
		moveComponent.setTurnRight(false);
	}

	@Override
	public void stop() {
	}

}
