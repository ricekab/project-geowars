package be.howest.twentytwo.parametergame.service.db;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataService implements IDataService {
//for the time being, this will have hard-coded data, and is later DELETED
	//	data management josb: faking access to MySQL data and return the appropriate data
	//	TODO make abstract class to manage DB systems?
	
	private Map<String, String> data = new HashMap<String, String>();
	
	
	public Map<String, String> getEnemy(String name) {
		data.clear();
		data.put("error", "none");
		name = name.toLowerCase();
		switch(name) {
			case "x-wing":
				data.put("speed mult", "100");
				data.put("health", "3");
				data.put("turn rate", "5");
				data.put("behaviour", "{\"spawn\":\"run\"}");
				break;
			case "tie-fighter":
				data.put("speed mult", "90");
				data.put("health", "2");
				data.put("turn rate", "1");
				data.put("behaviour", "{\"spawn\":\"run\",\"hit\":\"suicide\"}");
				break;
			case "deathstar":
				data.put("speed mult", "10");
				data.put("health", "1");
				data.put("turn rate", "10");
				data.put("behaviour", "stationary");
				break;
			default:
				data.put("error", "404 not found");
				break;
		}
		return data;
	}
	
	public Map<String, String> getShip(String name) {
		data.clear();
		data.put("error", "none");
		name = name.toLowerCase();
		switch(name) {
		case "starship enterprice":
			data.put("level", "15");
			data.put("exp", "7500");
			data.put("type", "juggernaut");
			data.put("speed mult", "50");
			data.put("drones", "{\"slot1\":\"uqq zhukov\", \"slot2\":\"uss farragut\", \"slot3\":\"uss valiant\"}");
			break;
		case "millenium falcon":
			data.put("level", "1");
			data.put("exp", "0");
			data.put("type", "recon");
			data.put("speed mult", "175");
			data.put("drones", "{\"slot1\":\"y-wing\", \"slot2\":\"null\", \"slot3\":\"null\"}");
			break;
		default:
			data.put("error", "404 not found");
			data.put("name", name);
			break;
		}
		return data;
	}
	
	public Map<String, String> getDrone(String name) {
		data.clear();
		data.put("error", "none");
		name = name.toLowerCase();
		switch(name) {
		case "uss zhukov":
			data.put("level", "1");
			data.put("exp", "57");
			data.put("effect", "collect");
			break;
		case "uss farragut":
			data.put("level", "3");
			data.put("exp", "517");
			data.put("effect", "speed mult");
			break;
		case "uss valiant":
			data.put("level", "10");
			data.put("exp", "9001");
			data.put("effect", "attack speed");
			break;
		case "y-wing":
			data.put("level", "30");
			data.put("exp", "0");
			data.put("effect", "bullet spread");
			break;
		default:
			data.put("error", "404 not found");
			break;
		}
		return data;
	}
}
