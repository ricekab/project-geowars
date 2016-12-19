package be.howest.twentytwo.parametergame.service.db;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.FixtureData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;

public class InMemoryDataService implements IDataService {
//for the time being, this will have hard-coded data, and is later DELETED
	//	data management josb: faking access to MySQL data and return the appropriate data

	@Override
	public UserData getUser(String serverID) {
		UserData data = new UserData("user", "USER");
		return data;
	}

	@Override
	public List<ShipData> getShips(UserData user) {
		List<ShipData> data = new ArrayList<>();
		PhysicsDataI physicsData = new PhysicsData(Constants.PLAYER_CATEGORY, Constants.PLAYER_COLLISION_MASK);
		physicsData.addFixture(new FixtureData("circle", 8f, 8f, 0, 0, 0.25f, 0.1f, 0f));
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataI weapon = new WeaponData("P001", 0f, 0f, 0.5f, 3, 9f, 1f, 100f, 500f, 0f, 5f, -1, new Vector2(1f, 0.25f));
		weapons.add(weapon);
		data.add(new ShipData("recon", 3, 50.0f, 30.0f, 25.0f, 20.0f, 0.1f, 1.0f, weapons, physicsData));
		return data;
	}

	@Override
	public List<DroneData> getDrones(UserData user) {
		List<DroneData> data = new ArrayList<>();
		data.add(new DroneData("dumbDrone", 0, 0));
		return data;
	}

	@Override
	public List<EnemyData> getEnemies(String... name) {
		List<EnemyData> data = new ArrayList<>();
		PhysicsDataI physicsData = new PhysicsData(Constants.ENEMY_CATEGORY, Constants.ENEMY_COLLISION_MASK);
		physicsData.addFixture(new FixtureData("Circle", 4f, 4f, 0, 0, 0.25f, 0.1f, 0f));
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataI weapon = new WeaponData("P001", 0f, 0f, 0.5f, 3, 9f, 1f, 100f, 500f, 0f, 5f, -1, new Vector2(1f, 0.25f));
		weapons.add(weapon);
		ShipData shipData = new ShipData("enemy01", 3, 30.0f, 30.0f, 10.0f, 10.0f, 0.1f, 1.0f, weapons, physicsData);
		data.add(new EnemyData(shipData));
		return data;
	}

	@Override
	public void saveUser(UserData data) {
		
	}

	@Override
	public void saveShip(ShipData data) {
		
	}

	@Override
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
