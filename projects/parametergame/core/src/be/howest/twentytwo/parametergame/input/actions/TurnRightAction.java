package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class TurnRightAction implements ConstantInputAction {

	private MovementComponent moveComponent;

	public TurnRightAction(MovementComponent mc) {
		this.moveComponent = mc;
	}

	@Override
	public void start() {
		moveComponent.setTurnRight(true);
	}

	@Override
	public void stop() {
		moveComponent.setTurnRight(false);
	}


}
