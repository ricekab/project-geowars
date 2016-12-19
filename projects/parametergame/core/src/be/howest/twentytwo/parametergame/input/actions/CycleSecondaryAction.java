package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class CycleSecondaryAction implements InputAction {

	private final WeaponComponent weapon;
	
	public CycleSecondaryAction(WeaponComponent weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public void start() {
		weapon.cycleSecondaryWeapon();
	}

	@Override
	public void stop() {
	}

}
