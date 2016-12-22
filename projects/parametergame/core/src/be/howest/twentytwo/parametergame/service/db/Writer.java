package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("nick","123456");

		Collection<PlayerShipDataI>playerShips = db.getPlayerShips(user);
		for(PlayerShipDataI playerShip : playerShips) {
			System.out.println(playerShip.getShipData().getGravityResistance());
		}
		
		
		
	}
}
