package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("Nick","123456");
		Collection<PlayerShipDataI> ships = db.getShips(user);
		for(PlayerShipDataI ship : ships) {
			System.out.println(ship.getShipData().getName());	
		}
		
	}
}
