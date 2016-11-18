package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Queue;
import java.util.Set;

public interface LevelDataI {
	
	public void setWorld(BoxData world);
	public void addPlanet(PlanetData planet);
	public void setSpawnBox(BoxData spawnBox);
	public void addSpawnPool(SpawnPoolData spawnPool);
	
	public BoxData getWorld();
	public Set<PlanetData> getPlanets();
	public BoxData getSpawnBox();
	public Queue<SpawnPoolData> getSpawnPools();

}
