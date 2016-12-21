package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("nick","123456");
		
		Collection<PlayerShipDataI> ships = db.getPlayerShips(user);		
		
		System.out.println("Ships: " + ships);
		for(PlayerShipDataI ship : ships) {
			System.out.println("ID: " + ship.getId());
			System.out.println("shipName: " + ship.getShipData().getName());
			for(WeaponDataI weapon : ship.getShipData().getWeapons()) {
				System.out.println("Actual weapon ID: " + weapon.getID());
			}
		}
		
	}
}
