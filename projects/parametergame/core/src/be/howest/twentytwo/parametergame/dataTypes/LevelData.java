package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelData {
	
	private BoxData world;
	private List<PlanetData> planets;
	private BoxData spawnBox;
	private Queue<SpawnPoolData> spawnpools;
	
	public LevelData(BoxData world, BoxData spawnBox) {
		this.planets = new ArrayList<>();
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
	
	public List<PlanetData> getPlanets() {
		return planets;
	}
	
	public BoxData getSpawnBox() {
		return spawnBox;
	}
	
	public Queue<SpawnPoolData> getSpawnpools() {
		return spawnpools;
	}

}
