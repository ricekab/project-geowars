package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShipData implements ShipDataI{
	
	private String name;
	private String texture;
	private int health;
	private float maxLinearSpeed;
	private float maxAngularSpeed;
	private float linearAcceleration;
	private float angularAcceleration;
	private float linearDamping;
	private float angularDamping;
	private List<WeaponDataI> weapons;
	private PhysicsDataI physicsData;
	private float shipSizeX;
	private float shipSizeY;
	
	public ShipData(String name, String texture, int health, float maxLinearSpeed, float maxAngularSpeed, float linearAcceleration, float angularAcceleration, float linearDamping, float angularDamping, List<WeaponDataI> weapons, PhysicsDataI physicsData, float shipSizeX, float shipSizeY){
		this.name = name;
		this.texture = texture;
		this.maxLinearSpeed = maxLinearSpeed;
		this.maxAngularSpeed = maxAngularSpeed;
		this.linearAcceleration = linearAcceleration;
		this.angularAcceleration = angularAcceleration;
		this.linearDamping = linearDamping;
		this.angularDamping = angularDamping;
		this.weapons = weapons;
		this.physicsData = physicsData;
		this.shipSizeX = shipSizeX;
		this.shipSizeY = shipSizeY;
	}
	
	//	GETTERS

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	public float getLinearAcceleration() {
		return linearAcceleration;
	}

	public float getAngularAcceleration() {
		return angularAcceleration;
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public float getAngularDamping() {
		return angularDamping;
	}

	public List<WeaponDataI> getWeapons() {
		return weapons;
	}

	public PhysicsDataI getPhysicsData() {
		return physicsData;
	}
	
	public float getShipSizeX() {
		return shipSizeX;
	}
	
	public float getShipSizeY() {
		return shipSizeY;
	}
	
	//	BUILDER
	
	public static class ShipDataBuilder{
		
		private String name;
		private String texture;
		private int health;
		private float maxLinearSpeed;
		private float maxAngularSpeed;
		private float linearAcceleration;
		private float angularAcceleration;
		private float linearDamping;
		private float angularDamping;
		private List<WeaponDataI> weapons;
		private PhysicsDataI physicsData;
		private float shipSizeX;
		private float shipSizeY;

		public ShipData build() {
			return new ShipData(name, texture, health, maxLinearSpeed, maxAngularSpeed, linearAcceleration, angularAcceleration, linearDamping, angularDamping, weapons, physicsData, shipSizeX, shipSizeY);
		}
		
		//	SETTERS

		public ShipDataBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public ShipDataBuilder setTexture(String texture) {
			this.texture = texture;
			return this;
		}

		public ShipDataBuilder setHealth(int health) {
			this.health = health;
			return this;
		}

		public ShipDataBuilder setMaxLinearSpeed(float maxLinearSpeed) {
			this.maxLinearSpeed = maxLinearSpeed;
			return this;
		}

		public ShipDataBuilder setMaxAngularSpeed(float maxAngularSpeed) {
			this.maxAngularSpeed = maxAngularSpeed;
			return this;
		}

		public ShipDataBuilder setLinearAcceleration(float linearAcceleration) {
			this.linearAcceleration = linearAcceleration;
			return this;
		}

		public ShipDataBuilder setAngularAcceleration(float angularAcceleration) {
			this.angularAcceleration = angularAcceleration;
			return this;
		}

		public ShipDataBuilder setLinearDamping(float linearDamping) {
			this.linearDamping = linearDamping;
			return this;
		}

		public ShipDataBuilder setAngularDamping(float angularDamping) {
			this.angularDamping = angularDamping;
			return this;
		}

		public ShipDataBuilder setWeapons(List<WeaponDataI> weapons) {
			this.weapons = weapons;
			return this;
		}

		public ShipDataBuilder setPhysicsData(PhysicsDataI physicsData) {
			this.physicsData = physicsData;
			return this;
		}

		public ShipDataBuilder setShipSizeX(float shipSizeX) {
			this.shipSizeX = shipSizeX;
			return this;
		}

		public ShipDataBuilder setShipSizeY(float shipSizeY) {
			this.shipSizeY = shipSizeY;
			return this;
		}
		
	}

}




