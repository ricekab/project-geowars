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
		Gdx.app.log("GS/PhysicsEntityListener", "Entity added");
	}

	@Override
	public void entityRemoved(Entity entity) {
		world.destroyBody(BodyComponent.MAPPER.get(entity).getBody()); // Remove body from world
	}

}
