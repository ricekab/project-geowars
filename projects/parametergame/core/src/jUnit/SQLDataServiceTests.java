package jUnit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.service.db.SQLDataService;

public class SQLDataServiceTests {
	
	private SQLDataService db;
	
	@BeforeClass
	public void initServer() {
		db = SQLDataService.getInstance();
	}
	
	@AfterClass
	public void teardownServer() {
		db.closeConnection();
	}


}