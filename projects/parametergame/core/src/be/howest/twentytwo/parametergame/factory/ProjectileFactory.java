package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.model.component.SpriteComponent;

public class ProjectileFactory {
	
	/** ID of the weapon this factory creates projectiles for. */
	private String weaponID;
	private BodyDef bodyDef;
	private FixtureDef[] fixtures;
	private SpriteComponent spriteComp;
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
		 * Sprite (--> shared)
		 * PhysicsBody
		 */
		
		return e;
	}

}
