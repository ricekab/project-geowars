package be.howest.twentytwo.parametergame.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipData.ShipDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

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

	public Collection<EnemyDataI> getEnemies(String... names) {
		Collection<EnemyDataI> enemies = null;
		String enemyNames = convertToSQLArray(names);
		System.out.println(enemyNames);
		try {
			String sql = "select e.ID, e.geomDroprate, e.baseScore, e.behaviour, s.* from parametergame.enemyShip e join parametergame.ship s on s.name = e.shipName where e.ID in ?"; //("bomber","fighter") valid format
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, enemyNames);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				ShipDataBuilder builder = new ShipData.ShipDataBuilder();
				ShipDataI ship = builder.setName(res.getString("name")).setHealth(res.getInt("health")).setLinearAcceleration(res.getFloat("linearAcceleration")).setAngularAcceleration(res.getFloat("angularAcceleration")).setMaxLinearSpeed(res.getFloat("maxLinearSpeed")).setMaxAngularSpeed(res.getFloat("maxAngularSpeed")).setTexture(res.getString("texture")).setLinearDamping(res.getFloat("linearDamping")).setAngularDamping(res.getFloat("angularDamping")).setShipSizeX(res.getFloat("shipSizeX")).setShipSizeY(res.getFloat("shipSizeY")).build();
				EnemyDataI enemyShip = new EnemyData(res.getString("ID"), res.getFloat("geomDroprate"), res.getInt("baseScore"), res.getString("behaviour"), ship);
				enemies.add(enemyShip);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return enemies;
	}

	public Collection<ShipDataI> getShips(UserDataI user) {
		return null;
	}

	public Collection<DroneDataI> getDrones(UserDataI user) {
		return null;
	}

	public void saveUser(UserDataI data) {

	}

	public void saveShip(ShipDataI data) {

	}

	public void saveDrone(DroneDataI data) {

	}
	
	private String convertToSQLArray(String... names) {
		String enemyNames = "(";
		for(int i = 0; i < names.length;) {
			enemyNames += "'" + names[i] + "'";
			i++;
			if(i < names.length) {
				enemyNames += ", ";
			}
		}
		enemyNames += ")";
		return enemyNames;
	}

}
