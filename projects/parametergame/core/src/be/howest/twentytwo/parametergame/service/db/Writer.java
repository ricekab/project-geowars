package be.howest.twentytwo.parametergame.service.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import be.howest.twentytwo.parametergame.dataTypes.*;

public class Writer {
	public static void main(String[] args) {

		//TESTS IF LOCALHOST WORKS
		IDataService db = SQLDataService.getInstance();
		System.out.println(db.getUser("Nick", "nickH4CK3D").getUser());
		
	}
}
