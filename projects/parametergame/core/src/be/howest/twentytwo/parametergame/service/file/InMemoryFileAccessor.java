package be.howest.twentytwo.parametergame.service.file;

import be.howest.twentytwo.parametergame.dataTypes.BoxData;
import be.howest.twentytwo.parametergame.dataTypes.BoxDataI;
import be.howest.twentytwo.parametergame.dataTypes.ClusterData;
import be.howest.twentytwo.parametergame.dataTypes.ClusterDataI;
import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlanetDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsData;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolData;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class InMemoryFileAccessor implements IFileAccessor {

	@Override
	public LevelDataI loadLevel(String levelName) {
		LevelDataI data = new LevelData();
		switch (levelName) {
			case "empty_planets.lvl":
				BoxDataI emptyWorld = new BoxData(500f, 500f, 0f, 0f);
				BoxDataI emptySpawnBox = new BoxData(5f, 5f, -5f, -5f);
				
				data.setWorld(emptyWorld);
				data.setSpawnBox(emptySpawnBox);
				
				data.addPlanet(new PlanetData(175, 100, 15, "planet0", 100f, 80f));
				data.addPlanet(new PlanetData(-75, -110, 26, "planet8", 200, 125));
				
				return data;
			case "level1.lvl":
			case "arcade1.lvl":
				BoxDataI world = new BoxData(1000f, 500f, 0f, 0f);
				BoxDataI spawnBox = new BoxData(5f, 5f, -5f, -5f);

				// /*LevelDataI*/ data = new LevelData();
				SpawnPoolDataI spawnPool = new SpawnPoolData();

				spawnPool.setSpawnTreshold(3f);
				spawnPool.setSpawnTresholdIncrease(0.5f);
				ClusterDataI cluster = new ClusterData(1f, 2, 0f, 2, 2, "scouter");
				spawnPool.addCluster(cluster);
				cluster = new ClusterData(1f, 2, 0f, 2, 1, "encloser");
				spawnPool.addCluster(cluster);
				data.addSpawnPool(spawnPool);

				spawnPool = new SpawnPoolData();
				spawnPool.setSpawnTreshold(5f);
				spawnPool.setSpawnTresholdIncrease(0.75f);
				cluster = new ClusterData(1f, 2, 1.5f, 2, 5, "scouter");
				spawnPool.addCluster(cluster);
				data.addSpawnPool(spawnPool);

				data.setWorld(world);
				data.setSpawnBox(spawnBox);
				PlanetDataI planet = new PlanetData(140, 140, 20, "planet0", 90f, 100f);
				data.addPlanet(planet);
				planet = new PlanetData(150f, -125, 5f, "planet5", 20f, 40f);
				data.addPlanet(planet);
				planet = new PlanetData(-50, -200, 30, "planet2", 125f, 135f);
				data.addPlanet(planet);
				planet = new PlanetData(300, 0, 12f, "planet3", 50f, 80f);
				data.addPlanet(planet);
				break;
			case "level2.lvl":
			case "arcade2.lvl":
				BoxDataI world2 = new BoxData(1000f, 500f, 0f, 0f);
				BoxDataI spawnBox2 = new BoxData(10f, 10f, -5f, -5f);

				// data = new LevelData();
				SpawnPoolDataI spawnPool2 = new SpawnPoolData();

				spawnPool2.setSpawnTreshold(10f);
				spawnPool2.setSpawnTresholdIncrease(2f);
				ClusterDataI cluster2 = new ClusterData(1f, 5, 10f, 5, 4, "encloser");
				spawnPool2.addCluster(cluster2);
				cluster2 = new ClusterData(1f, 25, 10f, 5, 2, "scouter");
				spawnPool2.addCluster(cluster2);
				data.addSpawnPool(spawnPool2);

				data.setWorld(world2);
				data.setSpawnBox(spawnBox2);
				PlanetDataI planet2 = new PlanetData(300, 300, 80f, "planet1", 600f, 300f);
				data.addPlanet(planet2);
				planet2 = new PlanetData(-100, -100, 25f, "planet4", 20f, 100f);
				data.addPlanet(planet2);
				break;
			case "hcf.lvl":
				BoxDataI hcfWorld = new BoxData(500f, 500f, 0f, 0f);
				BoxDataI hcfSpawnBox = new BoxData(5f, 5f, -5f, -5f);
		
				data.setWorld(hcfWorld);
				data.setSpawnBox(hcfSpawnBox);
				
				SpawnPoolDataI hcfSpawnPool = new SpawnPoolData();
				hcfSpawnPool.setSpawnTreshold(10f);
				hcfSpawnPool.setSpawnTresholdIncrease(2f);
				ClusterDataI hcfCluster = new ClusterData(1f, 25, 10f, 5, 5, "encloser");
				hcfSpawnPool.addCluster(hcfCluster);
				hcfCluster = new ClusterData(1f, 25, 7.5f, 5, 5, "scouter");
				hcfSpawnPool.addCluster(hcfCluster);
				
				data.addSpawnPool(hcfSpawnPool);
				
				data.addPlanet(new PlanetData(450, 125, 80, "planet7", 800f, 350));
				data.addPlanet(new PlanetData(-75, -110, 24, "planet9", 125, 125));
				
				return data;
			default:
				data = null; // Should be an exception instead of null
				break;
		}

		return data;
	}

	public void saveLevel(LevelDataI data, String saveLocation) {
		// Nothing actually happens
	}

	public SettingsDataI loadSettings(String justPutARandomString, UserDataI user) {
		SettingsDataI settings = new SettingsData(user);

		settings.setVolume(true);
		settings.setVolumeLevel(50f);
		settings.setDaming(true);
		settings.setDampingLevel(1f);

		return settings;
	}

	public void saveSettings(SettingsDataI settings, String saveLoction) {
		// Nothing actually happens
	}

}
