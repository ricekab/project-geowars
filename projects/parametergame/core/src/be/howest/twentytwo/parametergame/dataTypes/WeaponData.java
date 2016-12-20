package be.howest.twentytwo.parametergame.dataTypes;

import com.badlogic.gdx.math.Vector2;

public class WeaponData implements WeaponDataI {

	private String id;
	private float offsetX;
	private float offsetY;
	private float fireRate;
	private int bulletsPerShot;
	private float shotConeAngle;
	private float mass;
	private float bulletDamage;
	private float bulletSpeed;
	private float range;
	private float timeDelay;
	private float turnSpeed;
	private int ammoCount;
	private Vector2 bulletSize;

	public WeaponData(String id, float offsetX, float offsetY, float fireRate, int bulletsPerShot,
			float shotConeAngle, float damage, float bulletMass, float bulletSpeed, float range,
			float timeDelay, float turnSpeed, int ammoCount, Vector2 bulletSize) {
		this.id = id;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.fireRate = fireRate;
		this.bulletsPerShot = bulletsPerShot;
		this.shotConeAngle = shotConeAngle;
		this.bulletDamage = damage;
		this.mass = bulletMass;
		this.bulletSpeed = bulletSpeed;
		this.range = range;
		this.timeDelay = timeDelay;
		this.turnSpeed = turnSpeed;
		this.ammoCount = ammoCount;
		this.bulletSize = bulletSize;
	}

	// GETTERS

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

	@Override
	public float getBulletMass() {
		return mass;
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

	public int getAmmoCount() {
		return ammoCount;
	}

	public Vector2 getBulletSize() {
		return bulletSize;
	}

	public static class WeaponDataBuilder {

		private String id;
		private float offsetX;
		private float offsetY;
		private float fireRate;
		private int bulletsPerShot;
		private float shotConeAngle;
		private float mass;
		private float bulletDamage;
		private float bulletSpeed;
		private float range;
		private float timeDelay;
		private float turnSpeed;
		private int ammoCount;
		private Vector2 bulletSize;

		public WeaponData build() {
			return new WeaponData(id, offsetX, offsetY, fireRate, bulletsPerShot, shotConeAngle,
					bulletDamage, mass, bulletSpeed, range, timeDelay, turnSpeed, ammoCount,
					bulletSize);
		}

		public WeaponDataBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public WeaponDataBuilder setOffsetX(float offsetX) {
			this.offsetX = offsetX;
			return this;
		}

		public WeaponDataBuilder setOffsetY(float offsetY) {
			this.offsetY = offsetY;
			return this;
		}

		public WeaponDataBuilder setFireRate(float fireRate) {
			this.fireRate = fireRate;
			return this;
		}

		public WeaponDataBuilder setBulletsPerShot(int bulletsPerShot) {
			this.bulletsPerShot = bulletsPerShot;
			return this;
		}

		public WeaponDataBuilder setShotConeAngle(float shotConeAngle) {
			this.shotConeAngle = shotConeAngle;
			return this;
		}

		public WeaponDataBuilder setBulletMass(float mass) {
			this.mass = mass;
			return this;
		}

		public WeaponDataBuilder setBulletDamage(float bulletDamage) {
			this.bulletDamage = bulletDamage;
			return this;
		}

		public WeaponDataBuilder setBulletSpeed(float bulletSpeed) {
			this.bulletSpeed = bulletSpeed;
			return this;
		}

		public WeaponDataBuilder setRange(float range) {
			this.range = range;
			return this;
		}

		public WeaponDataBuilder setTimeDelay(float timeDelay) {
			this.timeDelay = timeDelay;
			return this;
		}

		public WeaponDataBuilder setTurnSpeed(float turnSpeed) {
			this.turnSpeed = turnSpeed;
			return this;
		}

		public WeaponDataBuilder setAmmoCount(int ammoCount) {
			this.ammoCount = ammoCount;
			return this;
		}

		public WeaponDataBuilder setBulletSize(Vector2 bulletSize) {
			this.bulletSize = bulletSize;
			return this;
		}

	}
}
