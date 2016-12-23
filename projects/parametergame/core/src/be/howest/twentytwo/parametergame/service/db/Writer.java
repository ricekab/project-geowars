package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		InMemoryDataService im = new InMemoryDataService();
		UserDataI user = db.getUser("Nick", "nickH4CK3D");
		Collection<PlayerShipDataI> ships = db.getPlayerShips(user);
		PlayerShipDataI ship = null;
		for(PlayerShipDataI s : ships) {
			ship = s;	//Set the last ship "s" as "ship"
		}
		
		WeaponDataI weapon = null;
		Collection<WeaponDataI> weapons = im.getWeapons(ship.getShipData());
		for(WeaponDataI w : weapons) {
			weapon = w;		//set the last weapon "w" as "weapon"
		}

		db.saveWeapon(weapon, ship.getShipData());

		
		
	}
}
