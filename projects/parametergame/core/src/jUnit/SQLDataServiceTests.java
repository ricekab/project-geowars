/*package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.service.db.SQLDataService;

public class SQLDataServiceTests {
	
	private SQLDataService db;
	
	@Before
	public void init() {
		db = SQLDataService.getInstance();
	}

	@Test
	public void testGetUser() {
		String desiredUsername = "The_Legend_27";
		UserDataI user = db.getUser(desiredUsername);
		assertEquals("\ntestGetUser failed\n", desiredUsername, user.getUser());
	}

}*/