package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface PlayerShipDataI {

	
	
	/*private String id;
	private float mass;
	private int exp;
	private int lvl;
	private float geomRadius;*/

	public void setShipData(ShipDataI ship);
	public void setDrones(Collection<DroneDataI> drones);
	
	public void setId(String id);
	public void setMass(float mass);
	public void setExp(int exp);
	public void setLvl(int lvl);
	public void setGeomRadius(float radius);

	
	public ShipDataI getShipData();
	public Collection<DroneDataI> getDrones();
	public void addDrone(DroneDataI drone);
	
	public String getId();
	public float getMass();
	public int getExp();
	public int getLvl();
	public float getGeomRadius();
}
