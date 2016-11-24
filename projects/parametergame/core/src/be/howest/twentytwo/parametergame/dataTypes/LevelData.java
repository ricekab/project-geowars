package be.howest.twentytwo.parametergame.dataTypes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LevelData implements LevelDataI, Serializable{
	
	private BoxDataI world;
	private Set<PlanetDataI> planets;
	private BoxDataI spawnBox;
	private Queue<SpawnPoolDataI> spawnpools;
	
	public LevelData() {	// I feel like we should be able to call this witouth putting data in it. to be reviewed
		this.planets = new HashSet<>();
		this.spawnpools = new LinkedList<>();
	}
	
	public LevelData(BoxDataI world, BoxDataI spawnBox) {
		this.planets = new HashSet<>();
		this.spawnpools = new LinkedList<>();
		setWorld(world);
		setSpawnBox(spawnBox);
	}
	
	//	SETTERS
	
	public void setWorld(BoxDataI world) {
		this.world = new BoxData(world.getWidth(), world.getHeight(), 0f, 0f);
	}
	
	public void addPlanet(PlanetDataI planet) {
		planets.add(planet);
	}
	
	public void setSpawnBox(BoxDataI spawnBox) {
		this.spawnBox = spawnBox;
	}
	
	public void addSpawnPool(SpawnPoolDataI spawnPool) {
		spawnpools.offer(spawnPool);
	}
	
	//	GETTERS
	
	public BoxDataI getWorld() {
		return world;
	};
	
	public Set<PlanetDataI> getPlanets() {
		return planets;
	}
	
	public BoxDataI getSpawnBox() {
		return spawnBox;
	}
	
	public Queue<SpawnPoolDataI> getSpawnPools() {
		return spawnpools;
	}

}
