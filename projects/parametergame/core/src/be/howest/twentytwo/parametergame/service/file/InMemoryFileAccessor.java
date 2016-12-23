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
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

public class InMemoryFileAccessor implements IFileAccessor {

	@Override
	public LevelDataI loadLevel(String justPutARandomString) {
		BoxDataI world = new BoxData(1000f, 500f, 0f, 0f);
		BoxDataI spawnBox = new BoxData(5f, 5f, -5f, -5f);

		LevelDataI data = new LevelData();
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
