package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		InMemoryDataService im = new InMemoryDataService();
		
		UserData user = new UserData("Nick","nick","Advanced JS difficulty");
		Collection<DroneDataI> drones =  db.getDrones(user);
		DroneDataI drone = null;
		for(DroneDataI d : drones) {
			drone = d;
		}
		//ABOVE GETS A DRONE
		
		System.out.println(drone.getOffenseUpgradeLevel());
		//PRINT THE LVL OF THE DRONE
		
		int i = drone.getOffenseUpgradeLevel();
		i--;
		//LVL -1
		
		drone.setOffenseUpgradeLevel(i);
		//WRITE LVL TO DRONE
		
		db.saveDrone(drone, user);
		//SAVE THE DRONE
		
		Collection<DroneDataI> dronezzz = db.getDrones(user);
		for(DroneDataI dr : dronezzz) {
			System.out.println(dr.getOffenseUpgradeLevel());
			//PRINT THE LVL OF THE DRONE FOR EACH DRONE
		}
		

		
		
	}
}
