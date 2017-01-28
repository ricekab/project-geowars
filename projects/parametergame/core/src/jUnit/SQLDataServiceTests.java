package jUnit;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.service.db.SQLDataService;

public class SQLDataServiceTests {
	
	private static SQLDataService db;
	
	@BeforeClass
	public static void initServer() {
		// db = SQLDataService.getInstance();
	}
	
	@AfterClass
	public static void teardownServer() {
		db.closeConnection();
	}
	
	@Before
	public void initVars() {
		
	}
	
	@Test
	public void testGetUser() {
		UserDataI defaultUserInDB = new UserData("user22","22","easy");
		UserDataI fetchedUser = db.getUser(defaultUserInDB.getUser(), defaultUserInDB.getPasswordHashed());
		assertEquals(defaultUserInDB.getUser(), fetchedUser.getUser());
	}
	
	@Test
	public void testGetEnemies() {
		String[] enemies = {"scouter","encloser"};
		Collection<EnemyDataI> enemyShips = db.getEnemies(enemies);
		for(EnemyDataI enemy : enemyShips) {
			System.out.println(enemy);
			System.out.println(enemy.getId());
		}
	}


}