package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Builds up the physics {@link World} as well as all game populates the ECS engine with level-defined entities.
 * 
 * TODO: Return should also return World object or ??? - Tentative change
 *
 */
public class WorldBuilder {

	public WorldBuilder() {
		// TODO Auto-generated constructor stub
	}

	public Engine buildWorld(LevelData levelData) {
		PooledEngine engine = new PooledEngine();

		return engine;
	}

}
