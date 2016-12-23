package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.PowerupDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;

public class PowerupFactory implements ISpawnFactory {
	private static final String PICKUPS_SPRITE_PACK = "sprites/game.pack";

	// TODO: IMPLEMENT PICKUPFACTORY FOR PICKUPS
	public PowerupFactory(PooledEngine engine, World world, AssetManager assets, PowerupDataI shipData,
			DifficultyDataI difficulty) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		return null;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return null;
	}

	@Override
	public String getType() {
		return null;
	}
	
	
	@Override
	public int hashCode() {
		return getType().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof PowerupFactory){
			PowerupFactory other = (PowerupFactory) obj;
			if(this.getType().equals(other.getType())){
				return true;
			}
		}
		return false;
	}

}
