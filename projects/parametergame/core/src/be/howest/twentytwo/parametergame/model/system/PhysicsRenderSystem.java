package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Debug renderer for the Box2D World.
 * 
 * @author Kevin CY Tang
 */
public class PhysicsRenderSystem extends IteratingSystem {

	private Box2DDebugRenderer renderer;
	private World world;
	private Camera cam;
	
	public PhysicsRenderSystem(World world, Camera cam) {
		super(Family.all(BodyComponent.class).get());
		this.world = world;
		this.cam = cam;
		renderer = new Box2DDebugRenderer();
	}

	@Override
	public void update(float deltaTime) {
		//super.update(deltaTime); // Don't need to iterate
		renderer.render(world, cam.combined);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// Not needed
	}
	
}