package be.howest.twentytwo.parametergame.dataTypes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LevelData implements LevelDataI, Serializable{
	
	private BoxData world;
	private Set<PlanetData> planets;
	private BoxData spawnBox;
	private Queue<SpawnPoolData> spawnpools;
	
	public LevelData() {	// I feel like we should be able to call this witouth putting data in it. to be reviewed
		this.planets = new HashSet<>();
		this.spawnpools = new LinkedList<>();
	}
	
	public LevelData(BoxData world, BoxData spawnBox) {
		this.planets = new HashSet<>();
		this.spawnpools = new LinkedList<>();
		setWorld(world);
		setSpawnBox(spawnBox);
	}
	
	//	SETTERS
	
	public void setWorld(BoxData world) {
		this.world = new BoxData(world.getWidth(), world.getHeight(), 0f, 0f);
	}
	
	public void addPlanet(PlanetData planet) {
		planets.add(planet);
	}
	
	public void setSpawnBox(BoxData spawnBox) {
		this.spawnBox = spawnBox;
	}
	
	public void addSpawnPool(SpawnPoolData spawnPool) {
		spawnpools.offer(spawnPool);
	}
	
	//	GETTERS
	
	public BoxData getWorld() {
		return world;
	};
	
	public Set<PlanetData> getPlanets() {
		return planets;
	}
	
	public BoxData getSpawnBox() {
		return spawnBox;
	}
	
	public Queue<SpawnPoolData> getSpawnPools() {
		return spawnpools;
	}

}
