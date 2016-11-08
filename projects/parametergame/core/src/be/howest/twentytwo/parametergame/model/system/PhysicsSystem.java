package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

/**
 * Drives the Box2D Physics simulation.
 * 
 * @author Kevin CY Tang
 */
public class PhysicsSystem extends IteratingSystem {
	
	public static final float PHYSICS_TIMESTEP = 1/30f;
	
	private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
	
	private World world;
	private float elapsed;

	public PhysicsSystem(World world) {
		super(Family.all(TransformComponent.class, BodyComponent.class).get());
		this.world = world;
		this.elapsed = 0f;
	}

	@Override
	public void update(float deltaTime) {
		elapsed += deltaTime;
		if(elapsed >= PHYSICS_TIMESTEP){	// World timestep
			world.step(PHYSICS_TIMESTEP, 6, 3);
			elapsed -= PHYSICS_TIMESTEP;
			super.update(deltaTime);	// Foreach -> processEntity
		}
		
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent transform = transformMapper.get(entity);
		BodyComponent body = bodyMapper.get(entity);
		
		transform.setPosition(body.getBody().getPosition());
		transform.setRotation(body.getBody().getAngle() * MathUtils.radiansToDegrees);
	}

}
