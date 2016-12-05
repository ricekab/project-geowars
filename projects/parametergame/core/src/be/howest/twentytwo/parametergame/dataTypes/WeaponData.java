package be.howest.twentytwo.parametergame.dataTypes;

public class WeaponData implements WeaponDataI{
	
	private String id;
	private float offsetX;
	private float offsetY;
	private float fireRate;
	private int bulletsPerShot;
	private float shotConeAngle;
	private float bulletDamage;
	private float bulletSpeed;
	private float range;
	private float timeDelay;
	private float turnSpeed;
	
	public WeaponData(String id, float offsetX, float offsetY, float fireRate, int bulletsPerShot, float shotConeAngle, float damage, float bulletSpeed, float range, float timeDelay, float turnSpeed) {
		this.id = id;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.fireRate = fireRate;
		this.bulletsPerShot = bulletsPerShot;
		this.shotConeAngle = shotConeAngle;
		this.bulletDamage = damage;
		this.bulletSpeed = bulletSpeed;
		this.range = range;
		this.timeDelay = timeDelay;
		this.turnSpeed = turnSpeed;
	}
	
	//	GETTERS

	public String getID() {
		return id;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public float getFireRate() {
		return fireRate;
	}

	public int getBulletsPerShot() {
		return bulletsPerShot;
	}

	public float getShotConeAngle() {
		return shotConeAngle;
	}

	public float getBulletDamage() {
		return bulletDamage;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public float getRange() {
		return range;
	}

	public float getTimeDelay() {
		return timeDelay;
	}
	
	public float getTurnSpeed() {
		return turnSpeed;
	}

}
