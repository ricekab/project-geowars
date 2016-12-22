package be.howest.twentytwo.parametergame.ui.data;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.screen.ShipLoadoutScreen;

/**
 * Collects the selected options from the {@link ShipLoadoutScreen}.
 */
public class LoadoutSelectionData {

	private ShipDataI ship;
	private DroneDataI drone;
	private DifficultyDataI difficulty;

	public LoadoutSelectionData(ShipDataI ship, DroneDataI drone, DifficultyDataI difficulty) {
		setShip(ship);
		setDrone(drone);
		setDifficulty(difficulty);
	}

	public ShipDataI getShip() {
		return ship;
	}

	public DroneDataI getDrone() {
		return drone;
	}

	public DifficultyDataI getDifficulty() {
		return difficulty;
	}

	public void setShip(ShipDataI ship) {
		this.ship = ship;
	}

	public void setDrone(DroneDataI drone) {
		this.drone = drone;
	}

	public void setDifficulty(DifficultyDataI difficulty) {
		this.difficulty = difficulty;
	}

}
