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
		Collection<PlayerShipDataI> playerShips = db.getPlayerShips(user);

		
		UserDataI u = db.getUser(user.getUser(), user.getPasswordHashed());
		System.out.println(u.getPasswordHashed());
		UserDataI u2 = new UserData(u.getUser(), u.getPasswordHashed() + "H4CK3D", u.getDifficulty());
		db.saveUser(u2);
		u = db.getUser(u2.getUser(), u2.getPasswordHashed());
		System.out.println(u.getPasswordHashed());
		
		
	}
}
