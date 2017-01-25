package be.howest.twentytwo.parametergame.model;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;

public class PhysicsBodyEntityListener implements EntityListener {

	private World world;
	private Family fam;

	public PhysicsBodyEntityListener(World world) {
		this.world = world;
		fam = Family.all(BodyComponent.class).get();
	}

	@Override
	public void entityAdded(Entity entity) {
		// Gdx.app.debug("EntityListener", "Entity added: " + entity);
	}

	@Override
	public void entityRemoved(Entity entity) {
		if(fam.matches(entity)){
			// Gdx.app.debug("BodyEntityListener", "Entity removed: " + entity);
			world.destroyBody(BodyComponent.MAPPER.get(entity).getBody()); // Remove body from world
		}
	}

}
