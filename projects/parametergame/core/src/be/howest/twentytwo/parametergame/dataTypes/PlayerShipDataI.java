package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface PlayerShipDataI {

	public ShipDataI getShipData();
	
	public Collection<DroneDataI> getDrones();
}
