package be.howest.twentytwo.parametergame.dataTypes;

public interface WeaponDataI {
	
	public String getID();	// = Name
	
	public float getOffsetX();
	public float getOffsetY();
	
	public float getFireRate();
	public int getBulletsPerShot();
	public float getShotConeAngle();
	
	public float getDamage();	// Per bullet
	public float getSpeed();	// Bullet speed
	public float getRange();	// Bullet fizzle range
	
	public String getTrigger();	// Possibility of special weapon triggers
}
