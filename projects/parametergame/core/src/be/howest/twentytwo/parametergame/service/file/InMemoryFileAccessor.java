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

public class InMemoryFileAccessor implements IFileAccessor{

	@Override
	public LevelDataI loadLevel(String justPutARandomString) {
		BoxDataI world = new BoxData(1000f, 500f, 0f, 0f);
		BoxDataI spawnBox = new BoxData(250f, 125f, 500f, 250f);
		PlanetDataI planet = new PlanetData(377f, 183f, 125f, "texture.png", 500f, 175f);
		ClusterDataI cluster = new ClusterData(1f, 1, 0f, 1, 1);
		// "boss" cluster: 1 group containging 1 enemy, 100% chance to spawn, no delay. exists only once in this case.
		SpawnPoolDataI spawnPool = new SpawnPoolData();
		LevelDataI data = new LevelData();
		
		spawnPool.setSpawnTreshold(3f);
		spawnPool.setSpawnTresholdIncrease(0.5f);
		spawnPool.addCluster(cluster);
		data.setWorld(world);
		data.setSpawnBox(spawnBox);
		data.addPlanet(planet);
		data.addSpawnPool(spawnPool);
		return data;
	}
	
	public void saveLevel(LevelDataI data, String saveLocation) {
		//Nothing actually happens
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
		//Nothing actually happens
	}
	
}
