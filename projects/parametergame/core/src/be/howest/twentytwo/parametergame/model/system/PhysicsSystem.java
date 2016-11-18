package be.howest.twentytwo.parametergame.model.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;

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

	private World world;
	/** Time elapsed since last update */
	private float elapsed;
	private Collection<IPhysicsEvent> eventCollection; // TODO: Collection requirements? Might need
														// change.

	public PhysicsSystem(World world, Collection<IPhysicsEvent> events) {
		// TODO: Think I should introduce a movement component?
		super(Family.all(TransformComponent.class, BodyComponent.class).get(), PRIORITY);
		this.world = world;
		this.elapsed = 0f;
		this.eventCollection = events;
	}

	public PhysicsSystem(World world) {
		this(world, new ArrayList<IPhysicsEvent>());
	}

	@Override
	public void update(float deltaTime) {
		elapsed += deltaTime;
		if(elapsed >= PHYSICS_TIMESTEP) { // World timestep
			processEvents(); // Process physics events (Collisions and input events)
			world.step(PHYSICS_TIMESTEP, 6, 3); // Advance simulation
			elapsed -= PHYSICS_TIMESTEP;
			super.update(deltaTime); // processEntity below
		}
	}

	private void processEvents() {
		Iterator<IPhysicsEvent> it = eventCollection.iterator();
		IPhysicsEvent evt;
		while (it.hasNext()) {
			evt = it.next();
			if(!evt.isConsumed()) {
				evt.execute();
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
