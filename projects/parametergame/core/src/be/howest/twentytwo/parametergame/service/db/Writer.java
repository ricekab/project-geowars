package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("nick","123456");

		String strings = "BMB01";
		Collection<EnemyDataI> enemies = db.getEnemies(strings);
		
		for(EnemyDataI enemy : enemies) {
			System.out.println("Enemy received in Writer: " + enemy);
			System.out.println("Enemy received has weapon: " + enemy.getShipData().getWeapons());
			System.out.println("Enemy received has physics: " + enemy.getShipData().getPhysicsData());
			System.out.println("Enemy received resists: " + (enemy.getShipData().getGravityResistance() * 100f) + " percent gravity!");	//TODO maybe i need to ask help on this...
		}
		
		
	}
}
