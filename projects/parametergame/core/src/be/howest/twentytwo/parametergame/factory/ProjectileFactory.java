package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

public class ProjectileFactory {
	
	/*
	 * Reusables
	 * ---
	 * BodyDef
	 * FixtureDef(s)
	 * Sprite(s)
	 */
	
	public Entity createProjectile(PooledEngine engine){
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
