package be.howest.twentytwo.parametergame.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashSet;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipData.ShipDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

public class SQLDataService implements IDataService {

	private static SQLDataService instance;
	private final String URL = "jdbc:mysql://localhost/parametergame";
	private final String USR = "user22"; // TODO change this
	private final String PWD = "22"; // TODO change this
	private Connection con;

	private SQLDataService() {
		try {
			con = DriverManager.getConnection(URL, USR, PWD);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to create a Connection...");
		}
	}

	public static SQLDataService getInstance() {
		if (instance == null) {
			instance = new SQLDataService();
		}
		return instance;
	}

	public UserDataI getUser(String username) {
		UserDataI user = null;
		try {
			String sql = "select * from parametergame.player where name = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, username);
			System.out.println(prep);
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				user = new UserData(res.getString("name"), res.getString("password"), res.getString("difficultyID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private EnemyDataI getEnemy(String name) {
		EnemyDataI enemyShip = null;
		try {
			String sql = "select e.ID, e.geomDroprate, e.baseScore, e.behaviour, s.* from parametergame.enemyShip e join parametergame.ship s on s.name = e.shipName where e.ID in (?)"; //("bomber","fighter") valid format
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, name);
			System.out.println(prep);
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				ShipDataBuilder builder = new ShipData.ShipDataBuilder();
				ShipDataI ship = builder.setName(res.getString("name")).setHealth(res.getInt("health")).setLinearAcceleration(res.getFloat("linearAcceleration")).setAngularAcceleration(res.getFloat("angularAcceleration")).setMaxLinearSpeed(res.getFloat("maxLinearSpeed")).setMaxAngularSpeed(res.getFloat("maxAngularSpeed")).setTexture(res.getString("texture")).setLinearDamping(res.getFloat("linearDamping")).setAngularDamping(res.getFloat("angularDamping")).setShipSizeX(res.getFloat("shipSizeX")).setShipSizeY(res.getFloat("shipSizeY")).build();
				enemyShip = new EnemyData(res.getString("ID"), res.getFloat("geomDroprate"), res.getInt("baseScore"), res.getString("behaviour"), ship);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return enemyShip;
	}	

	public Collection<EnemyDataI> getEnemies(String... names) {
		Collection<EnemyDataI> enemies = new HashSet<>();
		for(String name : names) {
			enemies.add(getEnemy(name));
		}
		return enemies;
	}

	public Collection<ShipDataI> getShips(UserDataI user) {	//NOTE this return ShipData, not PlayerShipData, cause turrets should be added somewhere else
		Collection<ShipDataI> ships = new HashSet<>();
		try {
			String sql = "select * from parametergame.playerShip ps join parametergame.playerShipProperty pp on ps.ID = pp.playerShipID join parametergame.player p on p.name = pp.playerName join parametergame.ship s on s.name = ps.shipName where p.name = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, user.getUser());
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				ShipDataBuilder builder = new ShipData.ShipDataBuilder();
				
				for(int i = 1; i < 23; i++){
					System.out.print(res.getString(i) + "\t");
				}
				System.out.println();
				
				ShipDataI ship = builder.setName(res.getString("name")).setHealth(res.getInt("health")).setLinearAcceleration(res.getFloat("linearAcceleration")).setAngularAcceleration(res.getFloat("angularAcceleration")).setMaxLinearSpeed(res.getFloat("maxLinearSpeed")).setMaxAngularSpeed(res.getFloat("maxAngularSpeed")).setTexture(res.getString("texture")).setLinearDamping(res.getFloat("linearDamping")).setAngularDamping(res.getFloat("angularDamping")).setShipSizeX(res.getFloat("shipSizeX")).setShipSizeY(res.getFloat("shipSizeY")).build();
				ships.add(ship);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ships;
	}

	public Collection<DroneDataI> getDrones(UserDataI user) {
		Collection<DroneDataI> drones = new HashSet<>();
		try {
			String sql = "select * from drone";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, user.getUser());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return drones;
	}
	
	public Collection<WeaponDataI> getWeapons(ShipDataI ship) {
		return null;
	}

	public void saveUser(UserDataI data) {

	}

	public void saveShip(ShipDataI data) {

	}

	public void saveDrone(DroneDataI data) {

	}
	
	public void saveWeapon(WeaponDataI weapon) {
		
	}

}
