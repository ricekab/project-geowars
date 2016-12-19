package be.howest.twentytwo.parametergame.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class SQLDataService implements IDataService{
	
	private static SQLDataService instance;
	private final String URL = "jdbc:mysql://localhost/mydatabase";
	private final String USR = "root";	//TODO change this
	private final String PWD = "";		//TODO change this
	
	private SQLDataService() {
		try{
			Connection con = DriverManager.getConnection(URL, USR, PWD);
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
		UserDataI u = new UserData("DELETE THIS","DELETE THIS");
		return u;
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
