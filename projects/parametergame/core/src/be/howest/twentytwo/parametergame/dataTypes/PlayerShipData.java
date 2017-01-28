package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;
import java.util.HashSet;

public class PlayerShipData implements PlayerShipDataI{
	
	private ShipDataI shipData;
	private Collection<DroneDataI> drones;
	
	private String id;
	private float mass;
	private int exp;
	private int lvl;
	private float geomRadius;
	private int campaignLevel;

	public PlayerShipData(ShipDataI ship, Collection<DroneDataI> drones, String id, float mass, int exp, int lvl, float geomRadius, int campaignLevel) {
		setShipData(ship);
		setDrones(drones);
		setId(id);
		setMass(mass);
		setExp(exp);
		setLvl(lvl);
		setGeomRadius(geomRadius);
		setCampaignLevel(campaignLevel);
	}
	
	public PlayerShipData(ShipDataI ship, String id, float mass, int exp, int lvl, float geomRadius, int campaignLevel) {
		this(ship, new HashSet<DroneDataI>(), id, mass, exp, lvl, geomRadius, campaignLevel);
	}	
	
	//TODO OLD CONSTRUCTORS BELOW
	@Deprecated
	public PlayerShipData(ShipDataI ship, Collection<DroneDataI> drones, String id, float mass, int exp, int lvl, float geomRadius) {
		setShipData(ship);
		setDrones(drones);
		setId(id);
		setMass(mass);
		setExp(exp);
		setLvl(lvl);
		setGeomRadius(geomRadius);
	}
	
	@Deprecated
	public PlayerShipData(ShipDataI ship, String id, float mass, int exp, int lvl, float geomRadius) {
		this(ship, new HashSet<DroneDataI>(), id, mass, exp, lvl, geomRadius);
	}
	
	@Deprecated
	public PlayerShipData(ShipDataI ship, Collection<DroneDataI> drones) {
		setShipData(ship);
		setDrones(drones);
	}
	
	@Deprecated
	public PlayerShipData(ShipDataI ship){
		this(ship, new HashSet<DroneDataI>());
	}
	//TODO OLD CONSTRUCTORS ABOVE
	
	//	SETTERS

	@Override
	public void setShipData(ShipDataI shipdata) {
		this.shipData = shipdata;
	}

	@Override
	public void setDrones(Collection<DroneDataI> droneCollection) {
		this.drones = droneCollection;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setMass(float mass) {
		this.mass = mass;
	}

	@Override
	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	@Override
	public void setGeomRadius(float radius) {
		this.geomRadius = radius;
	}

	@Override
	public void addDrone(DroneDataI drone) {
		drones.add(drone);
	}
	
	@Override
	public void setCampaignLevel(int campaignLevel) {
		this.campaignLevel = campaignLevel;
	}
	
	//	GETTERS

	@Override
	public ShipDataI getShipData() {
		return shipData;
	}

	@Override
	public Collection<DroneDataI> getDrones() {
		return drones;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public float getMass() {
		return mass;
	}

	@Override
	public int getExp() {
		return exp;
	}

	@Override
	public int getLvl() {
		return lvl;
	}

	@Override
	public float getGeomRadius() {
		return geomRadius;
	}
	
	public int getCampaignLevel() {
		return campaignLevel;
	}

}
