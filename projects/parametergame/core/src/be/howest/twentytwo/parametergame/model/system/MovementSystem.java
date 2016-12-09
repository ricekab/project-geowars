package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.model.physics.events.AngularForceEvent;
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
			/*
			 * Design decision - Apply force to reach max speed forward.
			 * This means force is not applied directly forward but towards the maximum forward vector.
			 * 
			 * TODO: Make a diagram for documents to help explain the concept.
			 * 
			 * Proof of concept (Only for forward)
			 * 
			 * TODO: This is still slightly broken, doesn't apply maximal force when reaching max speed.
			 * Clamping needs to be moved somewhere else?
			 */
			/*
			Gdx.app.log("MoveSys", String.format("Current linear velocity: %f", body.getLinearVelocity().len()));
			*/
			Vector2 bodyForwardUnitVector = body.getWorldVector(Vector2.X);
			Vector2 moveForwardVelocity = body.getLinearVelocity();
			Vector2 maxBodyForwardVec = new Vector2(bodyForwardUnitVector).scl(mc.getMaxLinearVelocity());
			
			/*
			Gdx.app.log("MoveSys", String.format("Direction vector %s", bodyForwardUnitVector.toString()));
			Gdx.app.log("MoveSys", String.format("Direction vector scaled %s", maxBodyForwardVec.toString()));
			Gdx.app.log("MoveSys", String.format("Linear v %s", moveForwardVelocity.toString()));
			*/
			Vector2 resultVector = maxBodyForwardVec.sub(moveForwardVelocity).clamp(0f, mc.getLinearAcceleration());
			/*
			Gdx.app.log("MoveSys", String.format("Result acceleration vector %s", maxBodyForwardVec.toString()));
			Gdx.app.log("MoveSys", String.format("Result acceleration length %f", maxBodyForwardVec.len()));
			*/
			resultVector.scl(body.getMass());	// F = ma
			/*
			Gdx.app.log("MoveSys", String.format("Result force vector %s", maxBodyForwardVec.toString()));
			Gdx.app.log("MoveSys", String.format("Result force length %f", maxBodyForwardVec.len()));
			*/
			events.add(new LinearForceEvent(body, resultVector));
			/*
			Gdx.app.log("MoveSys", "===");
			*/
			
		} else if (mc.isAccelerateBackward()) {
			Vector2 bodyBackwardUnitVector = body.getWorldVector(Vector2.X).scl(-1);
			Vector2 moveForwardVelocity = body.getLinearVelocity();
			Vector2 maxBodyBackwardVec = new Vector2(bodyBackwardUnitVector).scl(mc.getMaxLinearVelocity());
			
			Vector2 resultVector = maxBodyBackwardVec.sub(moveForwardVelocity).clamp(0f, mc.getLinearAcceleration());
		
			resultVector.scl(body.getMass());	// F = ma
			
			events.add(new LinearForceEvent(body, resultVector));
			
		}

		if (mc.isTurnLeft()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			float force = body.getMass() * actualAddedVelocity;

			events.add(new AngularForceEvent(body, force));
		} else if (mc.isTurnRight()) {
			float maxAddedVelocity = mc.getMaxAngularVelocity() - body.getAngularVelocity();
			float addedVelocity = mc.getAngularAcceleration();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);

			float force = body.getMass() * actualAddedVelocity;
			force *= -1;
			events.add(new AngularForceEvent(body, force));
		}

	}
}
