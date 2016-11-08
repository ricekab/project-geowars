package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	private OrthographicCamera cam;
	
	public PhysicsRenderSystem(World world, OrthographicCamera cam) {
		super(Family.all().get());	// Do I need any components? Don't need any to render the world.
		this.world = world;
		this.cam = cam;
		renderer = new Box2DDebugRenderer();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		renderer.render(world, cam.combined);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// Not needed
	}
	
}