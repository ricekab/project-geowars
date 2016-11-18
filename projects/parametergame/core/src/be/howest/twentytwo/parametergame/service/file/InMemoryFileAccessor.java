package be.howest.twentytwo.parametergame.service.file;

import be.howest.twentytwo.parametergame.dataTypes.BoxData;
import be.howest.twentytwo.parametergame.dataTypes.ClusterData;
import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolData;

public class InMemoryFileAccessor implements IFileAccessor{

	@Override
	public LevelData loadLevel(String justPutARandomString) {
		BoxData world = new BoxData(1000f, 500f, 0f, 0f);
		BoxData spawnBox = new BoxData(250f, 125f, 500f, 250f);
		PlanetData planet = new PlanetData(377f, 183f, 125f, "texture.png", 500f, 175f);
		ClusterData cluster = new ClusterData(1f, 1, 0f, 1, 1);
		// "boss" cluster: 1 group containging 1 enemy, 100% chance to spawn, no delay. exists only once in this case.
		SpawnPoolData spawnPool = new SpawnPoolData();
		LevelData data = new LevelData();
		
		spawnPool.setSpawnTreshold(3f);
		spawnPool.setSpawnTresholdIncrease(0.5f);
		spawnPool.addCluster(cluster);
		data.setWorld(world);
		data.setSpawnBox(spawnBox);
		data.addPlanet(planet);
		data.addSpawnPool(spawnPool);
		return data;
	}
	
	public void saveLevel(LevelData data, String saveLocation) {
		//Nothing actually happens
	}
	
}
