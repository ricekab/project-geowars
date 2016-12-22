package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;
import java.util.HashSet;

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
	private Collection<WeaponDataI> weapons;
	private PhysicsDataI physicsData;
	private float shipSizeX;
	private float shipSizeY;
	private float gravityResistance;
	
	public ShipData(String name, String texture, int health, float maxLinearSpeed, float maxAngularSpeed, float linearAcceleration, float angularAcceleration, float linearDamping, float angularDamping, Collection<WeaponDataI> weapons, PhysicsDataI physicsData, float shipSizeX, float shipSizeY, float gravityResistance){
		this.name = name;
		this.texture = texture;
		this.maxLinearSpeed = maxLinearSpeed;
		this.maxAngularSpeed = maxAngularSpeed;
		this.linearAcceleration = linearAcceleration;
		this.angularAcceleration = angularAcceleration;
		this.linearDamping = linearDamping;
		this.angularDamping = angularDamping;
		this.weapons = new HashSet<>();
		for(WeaponDataI weapon : weapons) {
			addWeapon(weapon);
		}
		this.physicsData = physicsData;
		this.shipSizeX = shipSizeX;
		this.shipSizeY = shipSizeY;
		this.gravityResistance = gravityResistance;
	}
	
	public void addWeapon(WeaponDataI weapon) {
		this.weapons.add(weapon);
	}
	
	//	GETTERS

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTexture() {
		return texture;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public float getLinearAcceleration() {
		return linearAcceleration;
	}

	@Override
	public float getAngularAcceleration() {
		return angularAcceleration;
	}

	@Override
	public float getLinearDamping() {
		return linearDamping;
	}

	@Override
	public float getAngularDamping() {
		return angularDamping;
	}

	@Override
	public Collection<WeaponDataI> getWeapons() {
		return weapons;
	}

	@Override
	public PhysicsDataI getPhysicsData() {
		return physicsData;
	}

	@Override
	public float getShipSizeX() {
		return shipSizeX;
	}

	@Override
	public float getShipSizeY() {
		return shipSizeY;
	}

	@Override
	public float getGravityResistance() {
		return gravityResistance;
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
		private Collection<WeaponDataI> weapons;	
		private PhysicsDataI physicsData;	
		private float shipSizeX;			
		private float shipSizeY;			
		private float gravityResistance;	

		public ShipData build() {
			//TEMP CODE BELOW
			for(WeaponDataI weapon : weapons) {
				System.out.println("logging weapons from within the builder: " + weapon.getID());	
			}
			//TEMP CODE ABOVE
			return new ShipData(name, texture, health, maxLinearSpeed, maxAngularSpeed, linearAcceleration, angularAcceleration, linearDamping, angularDamping, weapons, physicsData, shipSizeX, shipSizeY, gravityResistance);
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

		public ShipDataBuilder setWeapons(Collection<WeaponDataI> weapons) {
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
		
		public ShipDataBuilder setGravityResistance(float gravityResistance) {
			this.gravityResistance = gravityResistance;
			return this;
		}
		
	}

}




