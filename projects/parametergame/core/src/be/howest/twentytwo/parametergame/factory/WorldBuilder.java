package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Builds up the physics {@link World} as well as all game populates the ECS engine with
 * level-defined entities.
 * 
 * TODO: Return should also return World object or ??? - Tentative change
 * 
 * Note -- The ECS engine can listen for new entities added and add their body to the world. This
 * simplifies adding entities to the engine without having to pass the World around everywhere just
 * in case it's needed. The downside (?) is that the body definition is required as well.
 * 
 * Logically, it makes sense for most of the builders/factories to keep a copy of body def and
 * fixture def since they'll make a lot of copies. This basically becomes flyweight-esque.
 *
 */
public class WorldBuilder {

	public WorldBuilder() {
		// TODO Auto-generated constructor stub
	}

	public Engine buildWorld(LevelData levelData) {
		PooledEngine engine = new PooledEngine();

		World box2DWorld = new World(new Vector2(0f, 0f), true);

		// Add systems
		engine.addEntityListener(new PhysicsBodyEntityListener(box2DWorld));

		return engine;
	}

}
