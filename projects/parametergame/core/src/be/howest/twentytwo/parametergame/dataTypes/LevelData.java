package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.List;

public class LevelData {
	
	private Box world;
	private List<PlanetData> planets;
	private Box spawnBox;
	private List<SpawnPoolData> spawnpools;
	private float spawnTreshold;
	private float spawnTresholdIncrease;
	
	public LevelData() {
		this.planets = new ArrayList<>();
		this.spawnpools = new ArrayList<>();
	}
	
	//	SETTERS
	
	public void setWorld(float width, float height) {
		this.world = new Box(width, height, 0f, 0f);
	}
	
	public void addPlanet(float xCoord, float yCoord, float planetRadius, String texture, float mass, float gravityRadius) {
		planets.add(new PlanetData( xCoord, yCoord, planetRadius, texture, mass, gravityRadius));
	}
	
	public void setSpawnBox(float width, float height, float xCoord, float yCoord) {
		this.spawnBox = new Box(width, height, xCoord, yCoord);
	}
	
	public void addSpawnPool(/*TODO*/) {
		spawnpools.add(new SpawnPoolData(/*TODO*/));
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
	
	public List<SpawnPoolData> spawnpools() {
		return spawnpools;
	}
	
	public float getSpawnTreshold() {
		return spawnTreshold;
	}
	
	public float getSpawnTresholdIncrease() {
		return spawnTresholdIncrease;
	}

}
