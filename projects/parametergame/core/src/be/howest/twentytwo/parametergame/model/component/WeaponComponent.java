package be.howest.twentytwo.parametergame.model.component;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class WeaponComponent implements Component, Poolable {

	public static final ComponentMapper<WeaponComponent> MAPPER = ComponentMapper.getFor(WeaponComponent.class);

	// Input flags
	private boolean firePrimary;
	private boolean fireSecondary;

	private WeaponDataI primaryWeapon;
	private WeaponDataI secondaryWeapons[];
	private int activeSecondaryWeapon;

	public WeaponDataI getPrimary() {
		return primaryWeapon;
	}

	public void setPrimary(WeaponDataI primaryWeapon) {
		this.primaryWeapon = primaryWeapon;
	}

	private WeaponDataI[] getSecondaryWeapons() {
		return secondaryWeapons;
	}

	public void setSecondaryWeapons(WeaponDataI[] secondaryWeapons) {
		this.secondaryWeapons = secondaryWeapons;
		this.activeSecondaryWeapon = 0;
	}

	public WeaponDataI getActiveSecondaryWeapon() {
		return getSecondaryWeapons()[activeSecondaryWeapon];
	}

	public void cycleSecondaryWeapon() {
		activeSecondaryWeapon = (activeSecondaryWeapon + 1) % getSecondaryWeapons().length;
	}

	public boolean isFirePrimary() {
		return firePrimary;
	}

	public void setFirePrimary(boolean firePrimary) {
		this.firePrimary = firePrimary;
	}

	public boolean isFireSecondary() {
		return fireSecondary;
	}

	public void setFireSecondary(boolean fireSecondary) {
		this.fireSecondary = fireSecondary;
	}

	@Override
	public void reset() {
	}

}
