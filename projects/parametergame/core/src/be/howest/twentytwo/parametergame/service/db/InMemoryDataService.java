package be.howest.twentytwo.parametergame.service.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyData;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.FixtureData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.PowerupData;
import be.howest.twentytwo.parametergame.dataTypes.PowerupDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData.WeaponDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

public class InMemoryDataService implements IDataService {
	// for the time being, this will have hard-coded data, and is later DELETED
	// data management josb: faking access to MySQL data and return the
	// appropriate data

	@Override
	public UserData getUser(String name) { // password == name
		UserData data = new UserData(name, name);
		return data;
	}

	@Override
	public UserDataI getUser(String username, String hashedPassword) {
		return new UserData(username, hashedPassword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.howest.twentytwo.parametergame.service.db.IDataService#getShips(be. howest.twentytwo.
	 * parametergame.dataTypes.UserDataI)
	 * 
	 * @Return returns null for weapons, should be added after getting them from the DB separately.
	 */
	@Override
	@Deprecated
	public List<ShipDataI> getShips(UserDataI user) {
		List<ShipDataI> data = new ArrayList<>();
		PhysicsDataI physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("circle", 8f, 8f, 0, 0, 0.25f, 0.1f, 0f));

		// KEVIN: TEMPORARY MEEASURE FOR TESING -- ORIGINAL LINE BELOW
		List<WeaponDataI> weapons = new ArrayList<WeaponDataI>();
		weapons.addAll(getWeapons(null));
		ShipData ship = new ShipData("recon", "noTextureAvailable", 3, 50.0f, 30.0f, 25.0f, 20.0f,
				0.1f, 1.0f, weapons, physicsData, 10f, 10f, 0);
		data.add(ship);
		// END TEMPORARY MEEASURE FOR TESING

		// ORIGINAL LINE
		// data.add(new ShipData("recon", "noTextureAvailable", 3, 50.0f, 30.0f,
		// 25.0f, 20.0f, 0.1f,
		// 1.0f, null, physicsData, 10f, 10f));

		return data;
	}

	@Override
	public Collection<PlayerShipDataI> getPlayerShips(UserDataI user) {
		Collection<PlayerShipDataI> ships = new ArrayList<PlayerShipDataI>();
		// RECON
		PhysicsDataI physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("circle", 8f, 8f, 0, 0, 0.5f, 0.1f, 0f));

		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("bullet_basic").setOffsetX(0f).setOffsetY(0f)
				.setFireRate(5f).setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f)
				.setBulletSpeed(75f).setBulletMass(20f).setRange(250f)
				.setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(2f, 0.75f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		// new WeaponData("P001", 0f, 0f, 7.5f, 1, 0f, 1f, 5f, 75f, 1500f,0f,
		// 5f,
		// WeaponDataI.INFINITE_AMMO, new Vector2(1f, 0.25f));
		// TODO: Switch to builder to clarify arguments.
		WeaponDataI secondaryWeapon = new WeaponData("bullet_drone", 0f, 0f, 0.75f, 1, 0f, 1f, 10f, 100f,
				3500f, 0f, 0.5f, 25, new Vector2(4f, 1f));
		weapons.add(primaryWeapon);
		weapons.add(secondaryWeapon);
		ShipDataI ship = new ShipData("Recon", "recon", 3, 50.0f, 30.0f, 25.0f, 20.0f, 0f, 1.0f,
				weapons, physicsData, 8f, 8f, 5f);
		PlayerShipDataI playerShip = new PlayerShipData(ship, "Deadline", 10f, 0, 1, 50f);
		ships.add(playerShip);

		// JUGGERNAUT
		physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("box", 8f, 8f, 0, 0, 0.25f, 0.1f, 0f));
		ship = new ShipData("Juggernaught", "juggernaught", 3, 35.0f, 30.0f, 10.0f, 15.0f, 0f,
				0.5f, weapons, physicsData, 16f, 16f, 0.75f);
		playerShip = new PlayerShipData(ship, "Juggernaughty", 10f, 0, 1, 50f);
		ships.add(playerShip);

		return ships;
	}

	@Override
	public List<DroneDataI> getDrones(UserDataI user) {
		List<DroneDataI> data = new ArrayList<>();
		data.add(new DroneData("dumbDrone", 0, 0));
		return data;
	}

	@Override
	public List<EnemyDataI> getEnemies(String... name) {
		List<EnemyDataI> data = new ArrayList<>();
		PhysicsDataI physicsData = new PhysicsData(Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK);
		physicsData.addFixture(new FixtureData("circle", 6f, 6f, 0, 0, 0.5f, 0.1f, 0f));
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("bullet_shooter").setOffsetX(0f).setOffsetY(0f)
				.setFireRate(7.5f).setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f)
				.setBulletSpeed(75f).setBulletMass(5f).setRange(250f)
				.setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(1f, 0.25f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		// new WeaponData("P001", 0f, 0f, 7.5f, 1, 0f, 1f, 5f, 75f, 1500f,0f,
		// 5f,
		// WeaponDataI.INFINITE_AMMO, new Vector2(1f, 0.25f));
		// TODO: Switch to builder to clarify arguments.
		WeaponDataI secondaryWeapon = new WeaponData("W02", 0f, 0f, 0.75f, 1, 0f, 1f, 10f, 100f,
				3500f, 0f, 1f, 25, new Vector2(2.5f, 0.5f));
		weapons.add(primaryWeapon);
		weapons.add(secondaryWeapon);

		ShipData shipData = new ShipData("scouter", "scouter", 3, 30.0f, 30.0f, 10.0f, 10.0f, 0.1f,
				1.0f, weapons, physicsData, 8f, 8f, 1f);

		data.add(new EnemyData("scouter", 5f, 100, "Scouter", shipData));
		return data;
	}

	public Collection<WeaponDataI> getWeapons(ShipDataI ship) {
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("P001").setOffsetX(0f).setOffsetY(0f)
				.setFireRate(7.5f).setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f)
				.setBulletSpeed(75f).setBulletMass(5f).setRange(250f)
				.setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(1f, 0.25f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		// new WeaponData("P001", 0f, 0f, 7.5f, 1, 0f, 1f, 5f, 75f, 1500f,0f,
		// 5f,
		// WeaponDataI.INFINITE_AMMO, new Vector2(1f, 0.25f));
		// TODO: Switch to builder to clarify arguments.
		WeaponDataI secondaryWeapon = new WeaponData("W02", 0f, 0f, 0.75f, 1, 0f, 1f, 10f, 100f,
				3500f, 0f, 1f, 25, new Vector2(2.5f, 0.5f));
		weapons.add(primaryWeapon);
		weapons.add(secondaryWeapon);
		return weapons;
	}

	@Override
	public Collection<PowerupDataI> getPowerups() {
		Collection<PowerupDataI> powerups = new HashSet<>();
		powerups.add(new PowerupData("shiny orb", "redbull", -1, 180, "movementSpeed", 3));
		return powerups;
	}

	@Override
	public Collection<DifficultyDataI> getDifficulties() {
		Collection<DifficultyDataI> difficulties = new ArrayList<DifficultyDataI>();
		difficulties.add(new DifficultyData("Casual", 0.25f, 0.5f, 0.5f, 0.25f));
		difficulties.add(new DifficultyData("Normal", 1f, 1f, 1f, 1f));
		difficulties.add(new DifficultyData("Dark Souls", 3f, 3f, 4f, 5f));
		difficulties.add(new DifficultyData("Advanced JS", 0.1f, 0.33f, 0.2f, 25f));
		return difficulties;
	}

	@Override
	public void saveUser(UserDataI data) {

	}

	@Override
	public void saveShip(ShipDataI data) {

	}

	@Override
	public void saveDrone(DroneDataI data) {

	}

	@Override
	public void saveWeapon(WeaponDataI weapon) {

	}

	@Override
	public void savePlayerShip(PlayerShipDataI data) {
	}

}

/*
 * Collector drones effects
 * 
 * @param range(utility) the range in which geoms will be accelerated towards the ships current
 * position.
 * 
 * @param acceleration(power) the speed the geoms are accelerated with. this is uncapped, and is
 * only limited by the initial speed. geoms stop moving (?decelerate?) if the ship flies away and
 * they get out of the range again.
 * 
 * @return geoms are only collected once they reach the ship's actual collect position
 */

/*
 * Shooter drones effects
 * 
 * @param range(utility) the range in which it will shoot enemies.
 * 
 * @param attackSpeed(power) the speed at wich it shoots
 * 
 * @return always does one damage
 */

/*
 * gravitator drones effects
 * 
 * @param gravityReduction(utility) the percentage of gravity ignored
 * 
 * @param antigravitation(power) creates a negative gravity field, making it harder for enemies to
 * close in to you. (suicider & suicide squadron mainly)
 * 
 * @return has a static range, for antigravitation only
 */
