package be.howest.twentytwo.parametergame.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class SQLDataService implements IDataService{
	
	private static SQLDataService instance;
	private final String URL = "jdbc:mysql://localhost/parametergame";
	private final String USR = "user22";	//TODO change this
	private final String PWD = "22";		//TODO change this
	private Connection con;
	
	private SQLDataService() {
		try{
			con = DriverManager.getConnection(URL, USR, PWD);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed to create a Connection...");
		}
	}
	
	public static SQLDataService getInstance() {
		if(instance == null) {
			instance = new SQLDataService();
		}
		return instance;
	}
	

	public UserDataI getUser(String username) {
		//TODO USE FACTORY
		UserDataI user = null;
		try{
		String sql = "select * from parametergame.player where name = ?";
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setString(1, username);
		System.out.println(prep);	//TODO what the actual fuck
		ResultSet res = prep.executeQuery(sql);
		user = new UserData(res.getString("name"),res.getString("password"), res.getString("difficultyID"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public Collection<EnemyDataI> getEnemies(String... name) {
		return null;
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

}
