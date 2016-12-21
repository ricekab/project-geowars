package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.model.component.ai.AIComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;

public class AIShipFactory implements ISpawnFactory {      
	private ShipFactory shipFactory;
	private PooledEngine engine;
	private World world;
	private AssetManager assets;

	private Body target;
	private IAIMoveBehaviour moveBehaviour;
        private IAIShootBehaviour shootBehaviour;

	public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData, Body target,
			IAIMoveBehaviour moveBehaviour) {
            this(engine, world, assets, shipData, target);
            this.moveBehaviour = moveBehaviour;	
	}

        public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData, Body target,
			IAIMoveBehaviour moveBehaviour, IAIShootBehaviour shootBehaviour) {
            this(engine, world, assets, shipData, target, moveBehaviour);
            this.shootBehaviour = shootBehaviour;
        }

        public AIShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData, Body target) {
            this.shipFactory = new ShipFactory(engine, world, assets, shipData);
            this.engine = engine;
            this.world = world;
            this.assets = assets;
            this.target = target;
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