package be.howest.twentytwo.parametergame.dataTypes;

import com.badlogic.gdx.math.Vector2;

public interface WeaponDataI {
	
	public final static int INFINITE_AMMO = -1;
	
	public String getID();	// = Name
	
	public float getOffsetX();
	public float getOffsetY();
	
	public float getFireRate();
	public int getBulletsPerShot();
	public float getShotConeAngle();
	public int getAmmoCount();
	
	public Vector2 getBulletSize();	// TODO: Missing in information modelling
	public float getBulletDamage();	// Per bullet
	public float getBulletSpeed();	// Bullet speed
	public float getRange();	// Bullet fizzle range
	public float getTurnSpeed();	//How fast you can reposition the turret
	
	public float getTimeDelay();	// Delay after collision with enemy before the Object triggers. bullet will be 0, mine will have a delay.
}
