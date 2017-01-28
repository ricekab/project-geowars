package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class Writer {
	public static void main(String[] args) {

		//TESTS IF SERVER <3(c) WORKS
		IDataService db = new SQLDataService();
		UserDataI user = db.getUser("Nick", "nick");
		Collection<PlayerShipDataI> playerShips = db.getPlayerShips(user);

		String[] enemies = {"scouter","encloser"};
		Collection<EnemyDataI> enemyShips = db.getEnemies(enemies);
		for(EnemyDataI enemy : enemyShips) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(enemy);
			System.out.println(enemy.getId());
			System.out.println(enemy.getShipData());
			System.out.println(enemy.getShipData().getName());
		}
	}
}
