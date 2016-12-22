package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;
import java.util.List;

public interface ShipDataI {

	public String getName();	// Used to retrieve sprites
	public String getTexture();	// Better way to retrieve sprites
	
	public int getHealth();
	
	public float getMaxLinearSpeed();
	public float getMaxAngularSpeed();
	public float getLinearAcceleration();
	public float getAngularAcceleration();
	public float getLinearDamping();
	public float getAngularDamping();
	public float getShipSizeX();
	public float getShipSizeY();
	public float getGravityResistance();
	
	/**
	 * 
	 */
	public Collection<WeaponDataI> getWeapons();
	
	public PhysicsDataI getPhysicsData();
	
}
