package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

/*
 * Reads all data regarding the game, but not the level. This contains data about users, ships, drones & enemies
 */
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public interface IDataService {
	//This is the general blueprint for different classes, such as MySQLDataService, T-SQLDataService,....
	
	public UserDataI getUser(String username);
	
	public Collection<EnemyDataI> getEnemies(String... name);	//allows you to write getEnemies("Str1","Str2) instead of getEnemies(Str[])
	
	public Collection<PlayerShipDataI> getShips(UserDataI user);
	
	public Collection<DroneDataI> getDrones(UserDataI user);
	
	public void saveUser(UserDataI data);
	
	public void saveShip(ShipDataI data);
	
	public void saveDrone(DroneDataI data);
	
}
