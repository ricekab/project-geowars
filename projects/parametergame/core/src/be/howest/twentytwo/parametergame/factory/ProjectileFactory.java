package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;

public class ProjectileFactory {
	
	/*
	 * Reusables
	 * ---
	 * BodyDef
	 * FixtureDef(s)
	 * Sprite(s)
	 */
	
	public Entity createProjectile(PooledEngine engine, World world){
		Entity e = engine.createEntity();
		
		/*
		 * Required components
		 * ---
		 * Transform
		 * Movement
		 * Sprite
		 * PhysicsBody
		 */
		
		return e;
	}

}
