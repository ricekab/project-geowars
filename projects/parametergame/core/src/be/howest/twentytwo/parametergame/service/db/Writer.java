package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("Nick","123456");
		Collection<ShipDataI> ships = db.getShips(user);
		for(ShipDataI ship : ships) {
			System.out.println(ship.getTexture());	//TODO this works, health is 0, but 2 in db??? other values?	
		}
		
	}
}
