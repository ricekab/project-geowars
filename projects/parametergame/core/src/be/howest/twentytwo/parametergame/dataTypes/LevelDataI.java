package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Queue;
import java.util.Set;

public interface LevelDataI {

	public BoxDataI getWorld();
	public Set<PlanetDataI> getPlanets();
	public BoxDataI getSpawnBox();
	public Queue<SpawnPoolDataI> getSpawnPools();
	
	public void setWorld(BoxDataI world);
	public void addPlanet(PlanetDataI planet);
	public void setSpawnBox(BoxDataI spawnBox);
	public void addSpawnPool(SpawnPoolDataI spawnPool);
	

}
