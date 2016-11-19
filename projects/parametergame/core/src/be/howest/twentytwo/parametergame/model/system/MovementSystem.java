package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearImpulseEvent;
import be.howest.twentytwo.parametergame.model.physics.events.AngularImpulseEvent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

public class MovementSystem extends IntervalIteratingSystem {

	public final static int PRIORITY = 0;

	public Collection<IPhysicsEvent> events;

	public MovementSystem(Collection<IPhysicsEvent> events) {
		super(Family.all(MovementComponent.class, BodyComponent.class).get(), PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.events = events;
	}

	@Override
	protected void processEntity(Entity entity) {
		MovementComponent mc = MovementComponent.MAPPER.get(entity);
		Body body = BodyComponent.MAPPER.get(entity).getBody();

		// TODO: Extract this into a group of state classes to composite
		// MovementComponent
		if (mc.isAccelerateForward()) {
			// Split into forward and side.
			// for both forward and side do:
			//
			float addedVelocity = mc.getLinearAcceleration() * PhysicsSystem.PHYSICS_TIMESTEP;
			float maxAddedVelocity = mc.getMaxLinearVelocity() - body.getLinearVelocity().len();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			// F = ma
			float addImpulse = body.getMass() * actualAddedVelocity;
			Vector2 addImpulseVector = VectorMath.forceToForwardVector(addImpulse, body.getAngle());

			events.add(new LinearImpulseEvent(body, addImpulseVector));
		} else if (mc.isAccelerateBackward()) {
			float addedVelocity = mc.getLinearAcceleration() * PhysicsSystem.PHYSICS_TIMESTEP;
			float maxAddedVelocity = mc.getMaxLinearVelocity() - body.getLinearVelocity().len();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			// F = ma
			float addImpulse = body.getMass() * actualAddedVelocity;
			Vector2 addImpulseVector = VectorMath.forceToForwardVector(addImpulse, body.getAngle()).scl(-1f, -1f);

			events.add(new LinearImpulseEvent(body, addImpulseVector));
		}

		if (mc.isTurnLeft()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration() * PhysicsSystem.PHYSICS_TIMESTEP;
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			float force = body.getMass() * actualAddedVelocity;

			events.add(new AngularImpulseEvent(body, force));
		} else if (mc.isTurnRight()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration() * PhysicsSystem.PHYSICS_TIMESTEP;
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			float force = body.getMass() * actualAddedVelocity;
			force *= -1;
			events.add(new AngularImpulseEvent(body, force));
		}

	}
}
