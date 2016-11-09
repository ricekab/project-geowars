package be.howest.twentytwo.parametergame.service.db;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataService implements IDataService {
//for the time being, this will have hard-coded data, and is later DELETED
	//	data management josb: faking access to MySQL data and return the appropriate data
	//	
	
	private Map<String, String> data = new HashMap<String, String>();
	
	
	public Map<String, String> getEnemy(String name) {
		data = null;
		switch(name) {
			case "X-Wing":
				data.put("speed mult", "100");
				data.put("health", "3");
				data.put("turn rate", "5");
				data.put("behaviour", "{spawn:run}");
				break;
			case "Tie-Fighter":
				data.put("speed mult", "90");
				data.put("health", "2");
				data.put("turn rate", "1");
				data.put("behaviour", "{spawn:run,hit:suicide");
				break;
			case "death-star":
				data.put("speed mult", "0");
				data.put("health", "1");
				data.put("turn rate", "10");
				data.put("behaviour", "stationary");
				break;
		}
		return data;
	}
	
	public Map<String, String> getShip(String name) {
		data = null;
		switch(name) {
		case "Starship Enterprice":
			data.put("level", "15");
			data.put("exp", "7500");
			data.put("type", "juggernaut");
			data.put("speed mult", "50");
			data.put("drones", "{slot1:USS Zhukov, slot2:USS Farragut, slot3:USS Valiant}");
			break;
		case "Millenium Falcon":
			data.put("level", "1");
			data.put("exp", "0");
			data.put("type", "recon");
			data.put("speed mult", "175");
			data.put("drones", "{slot1:Y-Wing, slot2:null, slot3:null}");
			break;
		}
		return data;
	}
	
	public Map<String, String> getDrone(String name) {
		data = null;
		switch(name) {
		case "USS zhukov":
			data.put("level", "1");
			data.put("exp", "57");
			data.put("effect", "collect");
			break;
		case "USS Farragut":
			data.put("level", "3");
			data.put("exp", "517");
			data.put("effect", "speed mult");
			break;
		case "USS Valiant":
			data.put("level", "10");
			data.put("exp", "9001");
			data.put("effect", "attack speed");
			break;
		case "Y-Wing":
			data.put("level", "30");
			data.put("exp", "0");
			data.put("effect", "bullet spread");
			break;
		}
		return data;
	}
}
