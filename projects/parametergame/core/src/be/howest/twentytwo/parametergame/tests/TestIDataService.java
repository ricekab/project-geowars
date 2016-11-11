package be.howest.twentytwo.parametergame.tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.json.*;

import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.db.InMemoryDataService;

import org.junit.Assert;

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
