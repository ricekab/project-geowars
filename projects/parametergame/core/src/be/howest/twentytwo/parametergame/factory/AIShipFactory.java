package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.DefaultDifficultyData;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.ai.NullAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.NullAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

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

	private DifficultyDataI difficulty;
	private Body target;
	private IAIMoveBehaviour moveBehaviour;
	private IAIShootBehaviour shootBehaviour;

	public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData,
			DifficultyDataI difficulty, Body target, IAIMoveBehaviour moveBehaviour,
			IAIShootBehaviour shootBehaviour) {
		this.shipFactory = new ShipFactory(engine, world, assets, shipData, difficulty);
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		this.difficulty = difficulty;
		this.target = target;
		this.moveBehaviour = moveBehaviour;
		this.shootBehaviour = shootBehaviour;
	}

	public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData,
			DifficultyDataI difficulty, Body target, IAIMoveBehaviour moveBehaviour) {
		this(engine, world, assets, shipData, difficulty, target, moveBehaviour,
				new NullAIShootBehaviour());
	}

	public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData,
			DifficultyDataI difficulty, Body target) {
		this(engine, world, assets, shipData, difficulty, target,
				new NullAIMoveBehaviour(), new NullAIShootBehaviour());
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity,
			short physicsCategory, short physicsMask) {
		Entity aiShip = shipFactory.createShip(pos, rotation, Collision.BULLET_ENEMY_CATEGORY,
				Collision.BULLET_ENEMY_MASK);
		AIComponent ai = engine.createComponent(AIComponent.class);
		ai.setMoveBehaviour(moveBehaviour);
		ai.setShootBehaviour(shootBehaviour);
		ai.setTarget(target);
		aiShip.add(ai);
		engine.addEntity(aiShip);
		return aiShip;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return spawnEntity(pos, rotation, initialVelocity, Collision.BULLET_ENEMY_CATEGORY,
				Collision.BULLET_ENEMY_MASK);
	}

	@Override
	public String getType() {
		return shipFactory.getType();
	}
}