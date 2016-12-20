package be.howest.twentytwo.parametergame.model.dataExtension;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

import com.badlogic.gdx.math.Vector2;

public class NullWeaponData implements WeaponDataI {

	public static final String NULL_WEAPON_STRING = "NULL_WEAPON";

	@Override
	public String getID() {
		return NULL_WEAPON_STRING;
	}

	@Override
	public float getOffsetX() {
		return 0;
	}

	@Override
	public float getOffsetY() {
		return 0;
	}

	@Override
	public float getFireRate() {
		return 1f;
	}

	@Override
	public int getBulletsPerShot() {
		return 0;
	}

	@Override
	public float getShotConeAngle() {
		return 0f;
	}

	@Override
	public int getAmmoCount() {
		return 0;
	}
	
	@Override
	public float getBulletMass() {
		return 1f;
	}

	@Override
	public Vector2 getBulletSize() {
		return new Vector2(0f, 0f);
	}

	@Override
	public float getBulletDamage() {
		return 0f;
	}

	@Override
	public float getBulletSpeed() {
		return 0f;
	}

	@Override
	public float getRange() {
		return 0f;
	}

	@Override
	public float getTurnSpeed() {
		return 1f;
	}

	@Override
	public float getTimeDelay() {
		return 0f;
	}

}
