package be.howest.twentytwo.parametergame.service.db;

import java.util.Map;

public interface IDataService {
	//This is the general class, that communicates with different classes, such as MySQLDataService, T-SQLDataService,....

	public Map<String, String> getEnemy(String name);
	
	public Map<String, String> getShip(String name);
	
	public Map<String, String> getDrone(String name);
	
}
