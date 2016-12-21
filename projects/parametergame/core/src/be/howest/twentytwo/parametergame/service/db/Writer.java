package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("nick","123456");
		
		Collection<DroneDataI> drones = db.getDrones(user);		
		
		for(DroneDataI drone : drones) {
			System.out.println(drone.getID());
		}
		
	}
}
