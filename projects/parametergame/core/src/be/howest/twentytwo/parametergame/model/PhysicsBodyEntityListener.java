package be.howest.twentytwo.parametergame.model;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsBodyEntityListener implements EntityListener {

	private World world;

	public PhysicsBodyEntityListener(World world) {
		this.world = world;
	}

	@Override
	public void entityAdded(Entity entity) {
		// TODO: world.createBody(...) here? 
		/* --> Body does not store bodyDef, would have to be part of bodyComponent.
		 * Is storing a BodyDef useful for anything other than this creation? Cloning perhaps?
		 * (Prototype object for spawning enemies, objects, ...)
		 */
		Gdx.app.log("PhysicsBodyEntityListener", "Entity added");
	}

	@Override
	public void entityRemoved(Entity entity) {
		world.destroyBody(BodyComponent.MAPPER.get(entity).getBody()); // Remove body from world
	}

}
