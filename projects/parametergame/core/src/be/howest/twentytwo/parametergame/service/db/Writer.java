package be.howest.twentytwo.parametergame.service.db;

import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class Writer {
	public static void main(String[] args) {
		IDataService DB = SQLDataService.getInstance();
		UserDataI user = DB.getUser("nick");
		System.out.println(user.getUser() + user.getPasswordHashed() + user.getDifficulty());
	}
}
