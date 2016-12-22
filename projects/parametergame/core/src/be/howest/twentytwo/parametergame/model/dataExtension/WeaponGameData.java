package be.howest.twentytwo.parametergame.model.dataExtension;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

import com.badlogic.gdx.math.Vector2;

/**
 * Wrapper around WeaponDataI to add more functionality to it and extract entity specific data (ammo
 * count)
 */
public class WeaponGameData implements WeaponDataI {

	private final WeaponDataI weapon;
	/** Time (in seconds) for it to cool down between shots. */
	private final float cooldownTime;

	private int ammoCount;
	private float currentCooldown;
	private DifficultyDataI modifier;

	public WeaponGameData(WeaponDataI weapon, DifficultyDataI difficulty) {
		this.weapon = weapon;
		this.cooldownTime = 1f / weapon.getFireRate();
		setAmmoCount(weapon.getAmmoCount());
		setCurrentCooldown(0f);
		this.modifier = difficulty;
	}

	/**
	 * Checks whether the weapon is ready to fire (in terms of cooldown). This does not account for
	 * ammo.
	 */
	public boolean isCooledDown() {
		return getCurrentCooldown() <= 0f ? true : false;
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
		setCurrentCooldown(cooldownTime);
	}

	/** Coolds down the weapon for the given amount of time */
	public void cooldown(float dt) {
		setCurrentCooldown(Math.max(-1f, getCurrentCooldown() - dt));
	}

	public float getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(float cd) {
		this.currentCooldown = cd;
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
		return ammoCount;
	}

	public void setAmmoCount(int ammoCount) {
		this.ammoCount = ammoCount;
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

}
