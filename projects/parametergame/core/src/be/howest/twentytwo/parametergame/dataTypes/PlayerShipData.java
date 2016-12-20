package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerShipData implements PlayerShipDataI{
	
	private ShipDataI shipData;
	private Collection<DroneDataI> drones;
	
	//TODO implement data from SQL
	
	public PlayerShipData(ShipDataI ship, Collection<DroneDataI> drones) {
		setShipData(ship);
		setDrones(drones);
	}
	
	public PlayerShipData(ShipDataI ship){
		this(ship, new ArrayList<DroneDataI>());
	}
	
	//	SETTERS

	public void setShipData(ShipDataI shipdata) {
		this.shipData = shipdata;
	}

	public void setDrones(Collection<DroneDataI> droneCollection) {
		this.drones = droneCollection;
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

}
