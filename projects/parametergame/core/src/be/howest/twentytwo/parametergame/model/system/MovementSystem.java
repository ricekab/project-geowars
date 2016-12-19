package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.message.AngularForceMessage;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.physics.message.LinearForceMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementSystem extends IntervalIteratingSystem {

	public final static int PRIORITY = 0;

	public Collection<IPhysicsMessage> events;

	public MovementSystem(Collection<IPhysicsMessage> events) {
		super(Family.all(MovementComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.events = events;
	}

	@Override
	protected void processEntity(Entity entity) {
		MovementComponent mc = MovementComponent.MAPPER.get(entity);
		Body body = BodyComponent.MAPPER.get(entity).getBody();

		// TODO: Extract this into a group of state classes to composite
		// MovementComponent
		if(mc.isAccelerateForward()) {
			Vector2 bodyForwardUnitVector = body.getWorldVector(Vector2.X);
			Vector2 moveForwardVelocity = body.getLinearVelocity();
			Vector2 maxBodyForwardVec = new Vector2(bodyForwardUnitVector).scl(mc
					.getMaxLinearVelocity());
			Vector2 resultVector = maxBodyForwardVec.sub(moveForwardVelocity).clamp(0f,
					mc.getLinearAcceleration());
			resultVector.scl(body.getMass()); // F = ma
			events.add(new LinearForceMessage(body, resultVector));
		} else if(mc.isAccelerateBackward()) {
			Vector2 bodyBackwardUnitVector = body.getWorldVector(Vector2.X).scl(-1);
			Vector2 moveForwardVelocity = body.getLinearVelocity();
			Vector2 maxBodyBackwardVec = new Vector2(bodyBackwardUnitVector).scl(mc
					.getMaxLinearVelocity());
			Vector2 resultVector = maxBodyBackwardVec.sub(moveForwardVelocity).clamp(0f,
					mc.getLinearAcceleration());
			resultVector.scl(body.getMass()); // F = ma
			events.add(new LinearForceMessage(body, resultVector));
		}
		
		if(mc.isTurnLeft()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);
			float force = body.getMass() * actualAddedVelocity;
			events.add(new AngularForceMessage(body, force));
		} else if(mc.isTurnRight()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);
			float force = body.getMass() * actualAddedVelocity;
			force *= -1;
			events.add(new AngularForceMessage(body, force));
		}

	}
}
