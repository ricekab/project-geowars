package be.howest.twentytwo.parametergame.service.db;

import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService DB = SQLDataService.getInstance();
		String[] stringArray = {"ein","zwei","drei"};
		String user = DB.convertToSQLArray(stringArray);
		System.out.println(user);
		
	}
}
