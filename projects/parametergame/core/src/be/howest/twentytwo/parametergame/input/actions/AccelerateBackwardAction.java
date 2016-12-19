package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class AccelerateBackwardAction implements InputAction {
	
	private MovementComponent moveComponent;

	public AccelerateBackwardAction(MovementComponent mc) {
		this.moveComponent = mc;
	}

	@Override
	public void start() {
		moveComponent.setAccelerateBackward(true);
	}

	@Override
	public void stop() {
		moveComponent.setAccelerateBackward(false);
	}

}
