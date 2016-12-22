package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {
		
		SQLDataService db = SQLDataService.getInstance();
		UserData user = new UserData("The_Legend_27","12345678","Advanced JS difficulty");

		db.saveUser(user);
		
		System.out.println(db.getUser("The_Legend_27").getPasswordHashed());
		
		
		
	}
}
