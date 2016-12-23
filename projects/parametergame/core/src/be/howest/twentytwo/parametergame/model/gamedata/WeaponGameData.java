package be.howest.twentytwo.parametergame.model.gamedata;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

/**
 * Wrapper around WeaponDataI to add more functionality to it and extract entity specific data (ammo
 * count)
 */
public class WeaponGameData implements WeaponDataI {

	private final WeaponDataI weapon;

	private Entity owner;
	private AmmoData ammo;
	private DifficultyDataI modifier;

	public WeaponGameData(Entity owner, WeaponDataI weapon, DifficultyDataI difficulty) {
		this.owner = owner;
		this.weapon = weapon;
		setAmmoData(new AmmoData(weapon.getAmmoCount(), 1f / weapon.getFireRate()));
		this.modifier = difficulty;
	}

	/**
	 * Checks whether the weapon is ready to fire (in terms of cooldown). This does not account for
	 * ammo.
	 */
	public boolean isCooledDown() {
		return getAmmoData().getCurrentCooldown() <= 0f ? true : false;
	}

	/**
	 * Shortcut method to apply cooldown and handle ammo count. Return true if it fired, false
	 * otherwise.
	 */
	public boolean fire() {
		if(!isCooledDown()) {
			return false; // Can't fire (cooldown)
		}
		if(getAmmoCount() != WeaponDataI.INFINITE_AMMO && getAmmoCount() <= 0) {
			return false; // Cant fire (Out of ammo)
		}
		// Can fire - decrement ammo and start cool down.
		if(getAmmoCount() != WeaponDataI.INFINITE_AMMO) {
			setAmmoCount(getAmmoCount() - 1);
		}
		resetCooldown();
		return true;
	}

	/** Starts the cooldown time on the weapon. */
	public void resetCooldown() {
		getAmmoData().setCurrentCooldown(getAmmoData().getCooldownTme());
	}

	/** Coolds down the weapon for the given amount of time */
	public void cooldown(float dt) {
		getAmmoData().setCurrentCooldown(Math.max(-1f, getAmmoData().getCurrentCooldown() - dt));
	}


	@Override
	public String getID() {
		return weapon.getID();
	}

	@Override
	public float getOffsetX() {
		return weapon.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return weapon.getOffsetY();
	}

	@Override
	public float getFireRate() {
		return weapon.getFireRate() * modifier.getFirerateModifier();
	}

	@Override
	public int getBulletsPerShot() {
		return weapon.getBulletsPerShot();
	}

	@Override
	public float getShotConeAngle() {
		return weapon.getShotConeAngle();
	}

	@Override
	public int getAmmoCount() {
		return getAmmoData().getAmmoCount();
	}

	public void setAmmoCount(int ammoCount) {
		getAmmoData().setAmmoCount(ammoCount);
	}

	@Override
	public float getBulletMass() {
		return weapon.getBulletMass();
	}

	@Override
	public Vector2 getBulletSize() {
		return weapon.getBulletSize();
	}

	@Override
	public float getBulletDamage() {
		return weapon.getBulletDamage();
	}

	@Override
	public float getBulletSpeed() {
		return weapon.getBulletSpeed();
	}

	@Override
	public float getRange() {
		return weapon.getRange();
	}

	@Override
	public float getTurnSpeed() {
		return weapon.getTurnSpeed();
	}

	@Override
	public float getTimeDelay() {
		return weapon.getTimeDelay();
	}

	public AmmoData getAmmoData() {
		return ammo;
	}

	public void setAmmoData(AmmoData ammo) {
		this.ammo = ammo;
	}

}
