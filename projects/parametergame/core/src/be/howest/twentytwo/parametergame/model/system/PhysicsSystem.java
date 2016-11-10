package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Drives the Box2D Physics simulation.
 * 
 * @author Kevin CY Tang
 */
public class PhysicsSystem extends IteratingSystem {

	public static final float PHYSICS_TIMESTEP = 1 / 30f;

	private static final ComponentMapper<TransformComponent> TRANSFORM_MAPPER = ComponentMapper
			.getFor(TransformComponent.class);
	private static final ComponentMapper<BodyComponent> BODY_MAPPER = ComponentMapper.getFor(BodyComponent.class);

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
		if(elapsed >= PHYSICS_TIMESTEP) { // World timestep
			world.step(PHYSICS_TIMESTEP, 6, 3);
			elapsed -= PHYSICS_TIMESTEP;
			super.update(deltaTime); // Foreach -> processEntity
		}

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent transformComp = TRANSFORM_MAPPER.get(entity);
		BodyComponent bodyComp = BODY_MAPPER.get(entity);
		
		Gdx.app.log("phsxSys", bodyComp.getBody().getLinearVelocity().toString());

		transformComp.setPosition(bodyComp.getBody().getPosition());
		transformComp.setRotation(bodyComp.getBody().getAngle() * MathUtils.radiansToDegrees);
	}

}
