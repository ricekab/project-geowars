package be.howest.twentytwo.parametergame.ui.data;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.screen.ShipLoadoutScreen;

/**
 * Collects the selected options from the {@link ShipLoadoutScreen}.
 */
public class LoadoutSelectionData {

	private PlayerShipDataI playerShip;
	private Collection<DroneDataI> drones;
	private DifficultyDataI difficulty;

	public LoadoutSelectionData(PlayerShipDataI ship, Collection<DroneDataI> drones, DifficultyDataI difficulty) {
		setShip(ship);
		setDrones(drones);
		setDifficulty(difficulty);
	}

	public PlayerShipDataI getPlayerShip() {
		return playerShip;
	}

	public Collection<DroneDataI> getDrones() {
		return drones;
	}

	public DifficultyDataI getDifficulty() {
		return difficulty;
	}

	public void setShip(PlayerShipDataI ship) {
		this.playerShip = ship;
	}

	public void setDrones(Collection<DroneDataI> drone) {
		this.drones = drone;
	}

	public void setDifficulty(DifficultyDataI difficulty) {
		this.difficulty = difficulty;
	}

}
