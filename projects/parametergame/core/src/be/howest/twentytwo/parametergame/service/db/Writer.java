package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("nick","123456");

		String strings = "BMB01";
		Collection<DifficultyDataI> difficulties = db.getDifficulties();
		for(DifficultyDataI difficulty : difficulties) {
			System.out.println(difficulty.getID());
		}
		
		System.out.println();
		
		Collection<PowerupDataI> powerups = db.getPowerups();
		for(PowerupDataI powerup : powerups) {
			System.out.println(powerup.getEffectId() + " " + powerup.getPowerupId());
		}
		
		
		
		
	}
}
