package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class AccelerateForwardAction implements InputAction {

	private MovementComponent moveComponent;

	public AccelerateForwardAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		moveComponent.setAccelerateForward(true);
	}

	@Override
	public void stop() {
		moveComponent.setAccelerateBackward(false);
	}

}
