package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelData {
	
	private Box world;
	private List<PlanetData> planets;
	private Box spawnBox;
	private Queue<SpawnPoolData> spawnpools;
	
	public LevelData(Box world, Box spawnBox) {
		this.planets = new ArrayList<>();
		this.spawnpools = new LinkedList<>();
		setWorld(world);
		setSpawnBox(spawnBox);
	}
	
	//	SETTERS
	
	public void setWorld(Box world) {
		this.world = new Box(world.getWidth(), world.getHeight(), 0f, 0f);
	}
	
	public void addPlanet(PlanetData planet) {
		planets.add(planet);
	}
	
	public void setSpawnBox(Box spawnBox) {
		this.spawnBox = spawnBox;
	}
	
	public void addSpawnPool(SpawnPoolData spawnPool) {
		spawnpools.offer(spawnPool);
	}
	
	//	GETTERS
	
	public Box getWorld() {
		return world;
	};
	
	public List<PlanetData> getPlanets() {
		return planets;
	}
	
	public Box getSpawnBox() {
		return spawnBox;
	}
	
	public Queue<SpawnPoolData> getSpawnpools() {
		return spawnpools;
	}

}
