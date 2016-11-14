package be.howest.twentytwo.parametergame.service.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class InMemoryDataService implements IDataService {
//for the time being, this will have hard-coded data, and is later DELETED
	//	data management josb: faking access to MySQL data and return the appropriate data
	//	TODO make abstract class to manage DB systems?
	
	public LevelData getLevel(String key) {
		LevelData data = new LevelData(new Box(0f, 0f, 0f, 0f), new Box(0f, 0f, 0f, 0f));
		return data;
	}
	
	public UserData getUser(String serverID) {
		UserData data = new UserData();
		return data;
	}
	
	public List<ShipData> getShips(UserData user) {
		List<ShipData> data = new ArrayList<>();
		data.add(new ShipData());
		return data;
	}
	
	public List<DroneData> getDrones(UserData user) {
		List<DroneData> data = new ArrayList<>();
		data.add(new DroneData());
		return data;
	}
	
	public List<EnemyData> getEnemies(String... name) {
		List<EnemyData> data = new ArrayList<>();
		data.add(new EnemyData());
		return data;
	}
	
	public void saveUser(UserData data) {
		
	}
	
	public void saveShip(ShipData data) {
		
	}
	
	public void saveDrone(DroneData data) {
		
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
