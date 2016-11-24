package be.howest.twentytwo.parametergame.tests;

import org.junit.*;

import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.db.InMemoryDataService;


public class TestIDataService {
	
	IDataService db;

	@Before
	public void prepare() {
		db = new InMemoryDataService();
	}
	
	@After
	public void teardown() {
		
	}
	
}
