package be.howest.twentytwo.parametergame.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyData;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.PowerupData;
import be.howest.twentytwo.parametergame.dataTypes.PowerupDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipData.ShipDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData.WeaponDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

public class SQLDataService implements IDataService {

	private static SQLDataService instance;
	private final String URL = "jdbc:mysql://localhost/parametergame"; // TODO create server login for this
	private final String USR = "user22";
	private final String PWD = "22";

	
	private Connection con;

	private SQLDataService() {
		try {
			con = DriverManager.getConnection(URL, USR, PWD);
		} catch(Exception e) {
			System.out.println("failed to create a server connection");
			try{
				con = DriverManager.getConnection(URL, USR, PWD);
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("failed to create local connection");
			}
			/*
			ex.printStackTrace();
			System.out.println("failed to create a Connection...");
			*/
		}
	}

	public static SQLDataService getInstance() {
		if (instance == null) {
			instance = new SQLDataService();
		}
		return instance;
	}
	
	public UserDataI getUser(String username, String hashedPassword) {
		UserDataI user = null;
		try {
			String sql = "select * from parametergame.player where name = ? and password = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, username);
			prep.setString(2, hashedPassword);
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				user = new UserData(res.getString("name"), res.getString("password"), res.getString("difficultyID"));
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserDataI getUser(String username) {		//TODO REMOVE
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
			res.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private EnemyDataI getEnemy(String name) {
		EnemyDataI enemyShip = null;
		try {
			String sql = "select e.ID, e.geomDroprate, e.baseScore, e.behaviour, s.* from parametergame.enemyShip e join parametergame.ship s on s.name = e.shipName where e.ID = ?"; //("bomber","fighter") valid format
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, name);
			System.out.println(prep);
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				ShipDataBuilder builder = new ShipData.ShipDataBuilder();
				ShipDataI ship = builder.setName(res.getString("name")).setHealth(res.getInt("health")).setLinearAcceleration(res.getFloat("linearAcceleration")).setAngularAcceleration(res.getFloat("angularAcceleration")).setMaxLinearSpeed(res.getFloat("maxLinearSpeed")).setMaxAngularSpeed(res.getFloat("maxAngularSpeed")).setTexture(res.getString("texture")).setLinearDamping(res.getFloat("linearDamping")).setAngularDamping(res.getFloat("angularDamping")).setShipSizeX(res.getFloat("shipSizeX")).setShipSizeY(res.getFloat("shipSizeY")).setWeapons(getWeapons(res.getString("name"))).setPhysicsData(getPhysics(res.getString("physicsDataID"))).setGravityResistance(res.getFloat("gravityResistance")).build();
				System.out.println("getEnemy ShipData is: " + ship);
				enemyShip = new EnemyData(res.getString("ID"), res.getFloat("geomDroprate"), res.getInt("baseScore"), res.getString("behaviour"), ship);
			} else {
				System.err.println("no enemy found for: " + name);
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("getEnemy returns: " + enemyShip);
		return enemyShip;
	}	

	public Collection<EnemyDataI> getEnemies(String... names) {
		Collection<EnemyDataI> enemies = new HashSet<>();
		for(String name : names) {
			enemies.add(getEnemy(name));
		}
		return enemies;
	}

	private Collection<WeaponDataI> getWeapons(String shipName) {
		Collection<WeaponDataI> weapons = new HashSet<>();
		try {
			String sql = "select * from parametergame.weapon where shipName = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, shipName);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
				WeaponDataI weapon = builder.setId(res.getString("ID")).setOffsetX(res.getFloat("offsetX")).setOffsetY(res.getFloat("offsetY")).setBulletDamage(res.getFloat("bulletDamage")).setShotConeAngle(res.getFloat("shotConeAngle")).setFireRate(res.getFloat("firerate")).setRange(res.getFloat("range")).setTimeDelay(res.getFloat("detonationDelay")).setBulletsPerShot(res.getInt("bulletsPerShot")).setBulletSpeed(res.getFloat("bulletSpeed")).setBulletMass(res.getFloat("bulletMass")).setTurnSpeed(res.getFloat("turnSpeed")).setAmmoCount(res.getInt("ammo")).setBulletSize(new Vector2(res.getFloat("bulletSizeX"), res.getFloat("bulletSizeY"))).build();
				weapons.add(weapon);
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return weapons;
	}

	private PhysicsDataI getPhysics(String physicsdataID) {
		PhysicsDataI physics = null;
		try {
			String sql = "select * from parametergame.physicsdata p where p.ID = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, physicsdataID);
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				physics = new PhysicsData(res.getShort("physicsCategory"),res.getShort("physicsMask"));
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return physics;
	}
	
	public Collection<PlayerShipDataI> getPlayerShips(UserDataI user) {
		Collection<PlayerShipDataI> playerShips = new HashSet<>();
		try{
			String sql = "select * from parametergame.playerShipProperty pp join parametergame.playerShip ps on pp.playerShipID = ps.ID join parametergame.ship s on s.name = ps.shipName where pp.playerName = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, user.getUser());
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				ShipDataBuilder builder = new ShipDataBuilder();
				ShipDataI ship = builder.setName(res.getString("name")).setHealth(res.getInt("health")).setLinearAcceleration(res.getFloat("linearAcceleration")).setAngularAcceleration(res.getFloat("angularAcceleration")).setMaxLinearSpeed(res.getFloat("maxLinearSpeed")).setMaxAngularSpeed(res.getFloat("maxAngularSpeed")).setTexture(res.getString("texture")).setLinearDamping(res.getFloat("linearDamping")).setAngularDamping(res.getFloat("angularDamping")).setShipSizeX(res.getFloat("shipSizeX")).setShipSizeY(res.getFloat("shipSizeY")).setGravityResistance(res.getFloat("gravityResistance")).setPhysicsData(getPhysics(res.getString("physicsdataID"))).setWeapons(getWeapons(res.getString("shipName"))).build();
				PlayerShipDataI playerShip = new PlayerShipData(ship, res.getString("ID"),res.getFloat("mass"), res.getInt("exp"), res.getInt("lvl"), res.getFloat("geomRadius"), res.getInt("campaignLevel"));
				playerShips.add(playerShip);
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return playerShips;
	}
	
	public Collection<ShipDataI> getShips(UserDataI user) {
		Collection<ShipDataI> playerShips = new HashSet<>();
		//TODO REMOVE
		return playerShips;
	}

	public Collection<DroneDataI> getDrones(UserDataI user) {
		Collection<DroneDataI> drones = new HashSet<>();
		try {
			String sql = "select * from parametergame.drone where playerName = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, user.getUser());
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				DroneDataI drone = new DroneData(res.getString("ID"), res.getInt("offenseUpgradeLevel"), res.getInt("utilityupgradeLevel"));
				drones.add(drone);
			}
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return drones;
	}
	
	public Collection<PowerupDataI> getPowerups() {
		Collection<PowerupDataI> powerups = new HashSet<>();
		try {
			String sql = "select * from parametergame.powerup p join parametergame.powerupEffect pe on p.ID = pe.powerupID join parametergame.effect e on e.ID = pe.effectID";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()) {
				PowerupDataI powerup = new PowerupData(res.getString("powerupID"), res.getString("effectID"), res.getInt("duration"), res.getInt("lifetime"), res.getString("type"), res.getInt("strength"));
				powerups.add(powerup);
			}
			stmt.close();
			res.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return powerups;
	}
	
	public Collection<DifficultyDataI> getDifficulties() {
		Collection<DifficultyDataI> difficulties = new HashSet<>();
		try {
			String sql = "select * from parametergame.difficulty";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()) {
				DifficultyDataI difficulty = new DifficultyData(res.getString("ID"), res.getFloat("healthModifier"), res.getFloat("movementModifier"), res.getFloat("firerateModifier"), res.getFloat("scoreModifier"));
				difficulties.add(difficulty);
			}
			stmt.close();
			res.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return difficulties;
	}
	

	public void saveUser(UserDataI data) {
		try {
			String sqlSave ="";
			String sql = "select * from parametergame.player where `name` = ?";	//TODO call getUser method
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, data.getUser());
			ResultSet res = prep.executeQuery();
			
			if(res.next()) {
				sqlSave= "update parametergame.player set `password`=?, `difficultyID`=? where `name` = ?";
			} else {
				sqlSave = "insert into parametergame.player(`password`,`difficultyID`,`name`) values (?, ?, ?)";
			}
			PreparedStatement ps = con.prepareStatement(sqlSave);
			ps.setString(1, data.getPasswordHashed());
			ps.setString(2, data.getDifficulty());
			ps.setString(3, data.getUser());
			ps.executeUpdate();
			ps.close();
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void saveShip(ShipDataI data) {	//TODO make private after once kevin doesn't use it anymore
		try{
			String sqlSave ="";
			String sql = "select * from parametergame.ship where name = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, data.getName());
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				sqlSave = "update parametergame.ship set `health`=?, `linearAcceleration`=?, `angularAcceleration`=?, `maxLinearSpeed`=?, `maxAngularSpeed`=?, `texture`=?, `linearDamping`=?, `angularDamping`=?, `shipSizeX`=?, `shipSizeY`=?, `gravityResistance`=? where name = ?";
			} else {
				sqlSave = "insert into parametergame.ship(`health`, `linearAcceleration`, `angularAcceleration`, `maxLinearSpeed`, `maxAngularSpeed`, `texture`, `linearDamping`, `angularDamping`, `shipSizeX`, `shipSizeY`, `gravityResistance`, `name`) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			PreparedStatement ps = con.prepareStatement(sqlSave);
			ps.setInt(1, data.getHealth());
			ps.setFloat(2, data.getLinearAcceleration());
			ps.setFloat(3, data.getAngularAcceleration());
			ps.setFloat(4, data.getMaxLinearSpeed());
			ps.setFloat(5, data.getMaxAngularSpeed());
			ps.setString(6, data.getTexture());
			ps.setFloat(7, data.getLinearDamping());
			ps.setFloat(8, data.getAngularDamping());
			ps.setFloat(9, data.getShipSizeX());
			ps.setFloat(10, data.getShipSizeY());
			ps.setFloat(11, data.getGravityResistance());
			ps.setString(12, data.getName());
			ps.close();
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void savePlayerShip(PlayerShipDataI data) {
		try {
			String sqlSave = "";
			String sql = "select * from parametergame.playerShip where `ID` = ?";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, data.getId());
			ResultSet res = prep.executeQuery();
			if(res.next()) {
				sqlSave = "update parametergame.playerShip set `mass`=?, `exp`=?, `lvl`=?, `shipName`=?, `campaignLevel`=?, `geomRadius`=? where `ID` = ?";
			} else {
				saveShip(data.getShipData());
				sqlSave = "insert into parametergame.playerShip(`mass`,`exp`,`lvl`,`shipName`,`campaignLevel`,`geomRadius`,`ID`) values(?, ?, ?, ?, ?, ?, ?)";
			}
			PreparedStatement ps = con.prepareStatement(sqlSave);
			System.out.println(ps);
			ps.setFloat(1, data.getMass());
			ps.setInt(2, data.getExp());
			ps.setInt(3, data.getLvl());
			ps.setString(4, data.getShipData().getName());
			ps.setInt(5, data.getCampaignLevel());
			ps.setFloat(6, data.getGeomRadius());
			ps.setString(7, data.getId());
			System.out.println(ps);
			ps.executeUpdate();
			ps.close();
			res.close();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDrone(DroneDataI data) {

	}
	
	public void saveWeapon(WeaponDataI weapon) {
		
	}

}
