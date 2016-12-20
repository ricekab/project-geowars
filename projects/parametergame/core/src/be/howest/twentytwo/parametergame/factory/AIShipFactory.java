package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.model.ai.AIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.SimpleAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.component.ai.AIComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class AIShipFactory implements ISpawnFactory {

	private ShipFactory shipFactory;

	private PooledEngine engine;
	private World world;
	private AssetManager assets;

	private Body target;
	private AIMoveBehaviour moveBehaviour;

	public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData, Body target,
			AIMoveBehaviour moveBehaviour) {
		this.shipFactory = new ShipFactory(engine, world, assets, shipData);
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		this.target = target;
		this.moveBehaviour = moveBehaviour;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		Entity aiShip = shipFactory.createShip(pos, rotation, Constants.BULLET_ENEMY_CATEGORY,
				Constants.BULLET_ENEMY_MASK);
		AIComponent ai = engine.createComponent(AIComponent.class);
		ai.setMoveBehaviour(moveBehaviour);
		ai.setTarget(target);
		aiShip.add(ai);
		engine.addEntity(aiShip);
		return aiShip;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return spawnEntity(pos, rotation, initialVelocity, Constants.BULLET_ENEMY_CATEGORY,
				Constants.BULLET_ENEMY_MASK);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}