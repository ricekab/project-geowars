package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface ShipDataI {

	public String getName();	// Used to retrieve sprites
	
	public int getHealth();
	
	public float getMaxLinearSpeed();
	public float getMaxAngularSpeed();
	public float getLinearAcceleration();
	public float getAngularAcceleration();
	public float getLinearDamping();
	public float getAngularDampoing();
	
	public Collection<WeaponDataI> getWeapons();
	
	public PhysicsDataI getPhysicsData();
}
