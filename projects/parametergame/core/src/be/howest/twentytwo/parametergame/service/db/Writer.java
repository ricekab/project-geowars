package be.howest.twentytwo.parametergame.service.db;

import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		String[] names = {"BMB01","RCN42"};
		System.out.println(db.getEnemies(names));
		String[] name = {"BMB01"};
		System.out.println(db.getEnemies(name));
		
	}
}
