package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class TurnLeftAction implements InputAction {

	private MovementComponent moveComponent;

	public TurnLeftAction(MovementComponent mc) {
		this.moveComponent = mc;
	}

	@Override
	public void start() {
		moveComponent.setTurnLeft(true);
	}

	@Override
	public void stop() {
		moveComponent.setTurnLeft(false);
	}


}
