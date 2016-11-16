package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;

public class WorldBuilder {
	
	public WorldBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public Engine buildWorld(LevelData levelData){
		PooledEngine engine = new PooledEngine();
		
		return engine;
	}

}
