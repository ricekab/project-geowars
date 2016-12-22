package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		InMemoryDataService im = new InMemoryDataService();
		
		UserData user = new UserData("Nick","nick","Advanced JS difficulty");
		Collection<PlayerShipDataI> playerShips = im.getPlayerShips(user);

		for(PlayerShipDataI p : playerShips) {
			int campaignLevel = p.getCampaignLevel();
			System.out.println("\n----------------------------------------------------------------------------------------------------Ship: " + p.getShipData().getName());
			System.out.println("Initial campaign level: " + campaignLevel);
			campaignLevel++;
			p.setCampaignLevel(campaignLevel);
			db.savePlayerShip(p);
		}
		
		playerShips = db.getPlayerShips(user);
		
		for(PlayerShipDataI p : playerShips) {
			System.out.println("Campaign level from db: " + p.getCampaignLevel());
		}
		
		
	}
}
