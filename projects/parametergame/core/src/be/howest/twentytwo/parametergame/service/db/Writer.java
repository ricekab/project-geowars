package be.howest.twentytwo.parametergame.service.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {

		//TESTS IF SERVER <3(c) WORKS
		IDataService db = SQLDataService.getInstance();
		UserDataI user = db.getUser("Nick", "nick");
		Collection<PlayerShipDataI> playerShips = db.getPlayerShips(user);
		for(PlayerShipDataI p : playerShips) {
			GameIdDataI game = new GameIdData("Classic","Advanced JS");
			Timestamp ts = Timestamp.valueOf("2016-12-23 21:14:34");
			db.saveScore(p, game, 9001, ts);
		}
		
	}
}
