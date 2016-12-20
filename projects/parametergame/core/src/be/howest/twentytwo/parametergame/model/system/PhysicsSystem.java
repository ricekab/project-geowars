package be.howest.twentytwo.parametergame.model.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Drives the Box2D Physics simulation.
 * 
 * @author Kevin CY Tang
 */
public class PhysicsSystem extends IteratingSystem {

	public static final int PRIORITY = 0;

	public static final float PHYSICS_TIMESTEP = 1 / 30f;
	public static final int VELOCITY_ITERATIONS = 6;
	public static final int POSITION_ITERATIONS = 3;

	private World world;
	/** Time elapsed since last update */
	private float elapsed;
	private Collection<IPhysicsMessage> eventCollection;

	public PhysicsSystem(World world, Collection<IPhysicsMessage> events) {
		super(Family.all(TransformComponent.class, BodyComponent.class).get(), PRIORITY);
		this.world = world;
		this.elapsed = 0f;
		this.eventCollection = events;
	}

	public PhysicsSystem(World world) {
		this(world, new ArrayList<IPhysicsMessage>());
	}

	@Override
	public void update(float deltaTime) {
		elapsed += deltaTime;
		if(elapsed >= PHYSICS_TIMESTEP) { // World timestep
			processEvents(); // Process physics events (Collisions and inputevents)
			world.step(PHYSICS_TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
			elapsed -= PHYSICS_TIMESTEP;
			super.update(deltaTime); // processEntity below
		}
	}

	private void processEvents() {
		Iterator<IPhysicsMessage> it = eventCollection.iterator();
		IPhysicsMessage evt;
		while (it.hasNext()) {
			evt = it.next();
			if(!evt.isConsumed()) {
				evt.execute();
				// Could remove this check -- Would be removed on the next pass.
				if(evt.isConsumed()) {
					it.remove();
				}
			} else {
				it.remove(); // Not redundant, other systems can affect events.
			}
		}
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent transformComp = TransformComponent.MAPPER.get(entity);
		BodyComponent bodyComp = BodyComponent.MAPPER.get(entity);

		// Update game object position based on physics body.
		transformComp.setPos(bodyComp.getBody().getPosition());
		transformComp.setRotation(bodyComp.getBody().getAngle() * MathUtils.radiansToDegrees);
	}

}
