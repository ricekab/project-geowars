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
		BoxDataI spawnBox = new BoxData(10f, 10f, -10f, -10f);

		LevelDataI data = new LevelData();
		SpawnPoolDataI spawnPool = new SpawnPoolData();

		spawnPool.setSpawnTreshold(3f);
		spawnPool.setSpawnTresholdIncrease(0.5f);
		ClusterDataI cluster = new ClusterData(1f, 1, 0f, 2, 2, "scouter");
		spawnPool.addCluster(cluster);
		cluster = new ClusterData(1f, 1, 0f, 2, 1, "encloser");
		spawnPool.addCluster(cluster);
		data.setWorld(world);
		data.setSpawnBox(spawnBox);
		PlanetDataI planet = new PlanetData(125, 125, 20, "planet0", 75f, 100f);
		data.addPlanet(planet);
		planet = new PlanetData(-50, -200, 30, "planet2", 125f, 135f);
		data.addPlanet(planet);
		data.addSpawnPool(spawnPool);
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
