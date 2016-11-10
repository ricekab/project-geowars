package be.howest.twentytwo.parametergame.service.db;

import java.util.Map;

public interface IDataService {
	//This is the general class, that communicates with different classes, such as MySQLDataService, T-SQLDataService,....

	public LevelData getLevel(String key);
	
	public UserData getUser(String serverID);
	
	public Collection<EnemyData> getEnemies(String... name);	//allows you to write getEnemies("Str1","Str2) instead of getEnemies(Str[])
	
	public Collection<ShipData> getShips(UserData user);
	
	public Collection<DroneData> getDrones(UserData user);
	
	public void saveUser(UserData);
	
	public void saveShip(ShipData);
	
	public void saveDrone(DroneData);
	
}
