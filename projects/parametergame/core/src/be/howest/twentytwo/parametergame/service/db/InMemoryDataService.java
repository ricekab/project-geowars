package be.howest.twentytwo.parametergame.service.db;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataService implements IDataService {
//for the time being, this will have hard-coded data, and is later DELETED
	//	data management josb: faking access to MySQL data and return the appropriate data
	//	TODO make abstract class to manage DB systems?
	
	private Map<String, String> data = new HashMap<String, String>();
	
	
	public Map<String, String> getShip(String shipID) {
		data.clear();
		data.put("name", "testShipRecon");
		data.put("type", "recon");	//recon-fighter-juggernaut
		data.put("level", "30");
		data.put("exp", "0");
		data.put("speed cap", "150");
		data.put("acceleration mult", "125");
		data.put("turn rate mult", "200");
		return data;
	}
	public Map<String, String> getDrone(String droneID) {
		data.clear();
		data.put("name", "testDroneOne");
		data.put("level", "10");
		data.put("exp", "0");
		data.put("type", "shooter");	//collecter-shooter-gravitator
		data.put("utilityUpgrade", "3");
		data.put("powerUpgrade", "3");
		return data;
	}
	public Map<String, String> getEnemy(String name) {
		data.clear();
		data.put("name","testEnemyOne");
		data.put("type","suicider");	//bomber-obstacle-scouter-suicideSquadron-suicider
		data.put("health","1");
		data.put("speed cap","200");
		data.put("acceleration mult","50");
		data.put("turn rate mult","500");
		data.put("attack speed", "0");
		return data;
	}
}


/*
 * Collector drones effects
 * @param range(utility) the range in which geoms will be accelerated towards the ships current position.
 * @param acceleration(power) the speed the geoms are accelerated with. this is uncapped, and is only limited by the initial speed. geoms stop moving (?decelerate?) if the ship flies away and they get out of the range again.
 * @return geoms are only collected once they reach the ship's actual collect position
 */

/*
 * Shooter drones effects
 * @param range(utility) the range in which it will shoot enemies.
 * @param attackSpeed(power) the speed at wich it shoots
 * @return always does one damage
 */

/*
 * gravitator drones effects
 * @param gravityReduction(utility) the percentage of gravity ignored
 * @param antigravitation(power) creates a negative gravity field, making it harder for enemies to close in to you. (suicider & suicide squadron mainly)
 * @return has a static range, for antigravitation only
 */
