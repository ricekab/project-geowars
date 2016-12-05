package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShipData implements ShipDataI{
	
	private String name;
	private int health;
	private float maxLinearSpeed;
	private float maxAngularSpeed;
	private float linearAcceleration;
	private float angularAcceleration;
	private float linearDamping;
	private float angularDamping;
	private List<WeaponDataI> weapons;
	private PhysicsDataI physicsData;
	
	public ShipData(String name, int health, float maxLinearSpeed, float maxAngularSpeed, float linearAcceleration, float angularAcceleration, float linearDamping, float angularDamping, PhysicsDataI physicsData){
		this.name = name;
		this.maxLinearSpeed = maxLinearSpeed;
		this.maxAngularSpeed = maxAngularSpeed;
		this.linearAcceleration = linearAcceleration;
		this.angularAcceleration = angularAcceleration;
		this.linearDamping = linearDamping;
		this.angularDamping = angularDamping;
		weapons = new ArrayList<>();
		this.physicsData = physicsData;
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

	public Collection<WeaponDataI> getWeapons() {
		return weapons;
	}

	public PhysicsDataI getPhysicsData() {
		return physicsData;
	}

}
