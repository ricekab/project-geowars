package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class AccelerateForwardAction implements KeyInputAction {

	private MovementComponent moveComponent;

	public AccelerateForwardAction(MovementComponent mc) {
		this.moveComponent = mc;
	}

	@Override
	public void start() {
		moveComponent.setAccelerateForward(true);
	}

	@Override
	public void stop() {
		moveComponent.setAccelerateForward(false);
	}

}
