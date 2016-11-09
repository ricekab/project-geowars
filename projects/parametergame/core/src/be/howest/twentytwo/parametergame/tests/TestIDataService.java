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
	
	@Test
	public void testGetEnemyWithCaps() {
		String cappedName = "DeAtHsTaR";
		String behaviour = db.getEnemy(cappedName).get("behaviour");
		String expected = "stationary";
		assertEquals(expected, behaviour);
	}
	
	@Test
	public void testGetShipDrone1() {
		String name = "Millenium falcon";
		String drones = db.getShip(name).get("drones");
		JSONObject json = new JSONObject(drones);
		String drone1 = (String) json.get("slot1");
		String expected = "y-wing";
		assertEquals("Expected " + expected + ", got " + drone1,expected, drone1);
	}
	
}
