package be.howest.twentytwo.parametergame.input.actions;

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class FirePrimaryAction implements InputAction {

	private WeaponComponent weapon;
	
	public FirePrimaryAction(WeaponComponent weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public void start() {
		weapon.setFirePrimary(true);
	}

	@Override
	public void stop() {
		weapon.setFirePrimary(false);
	}

}
