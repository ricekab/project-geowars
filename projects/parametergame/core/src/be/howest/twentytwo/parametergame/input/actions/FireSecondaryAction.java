package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class FireSecondaryAction implements InputAction {

	private final WeaponComponent weapon;

	public FireSecondaryAction(WeaponComponent weapon) {
		this.weapon = weapon;
	}

	@Override
	public void start() {
		weapon.setFireSecondary(true);
	}

	@Override
	public void stop() {
		weapon.setFireSecondary(false);
	}

}
