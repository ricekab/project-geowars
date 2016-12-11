package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;
/*
 * Reads all data regarding the game, but not the level. This contains data about users, ships, drones & enemies
 */
import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.UserData;

public interface IDataService {
	//This is the general blueprint for different classes, such as MySQLDataService, T-SQLDataService,....
	
	public UserData getUser(String serverID);
	
	public Collection<EnemyData> getEnemies(String... name);	//allows you to write getEnemies("Str1","Str2) instead of getEnemies(Str[])
	
	public Collection<ShipData> getShips(UserData user);
	
	public Collection<DroneData> getDrones(UserData user);
	
	public void saveUser(UserData data);
	
	public void saveShip(ShipData data);
	
	public void saveDrone(DroneData data);
	
}
