package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementSystem extends IntervalIteratingSystem {

	public final static int PRIORITY = 0;

	public Collection<IPhysicsEvent> events;

	public MovementSystem(Family family) {
		super(Family.all(MovementComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity) {
		MovementComponent mc = MovementComponent.MAPPER.get(entity);
		Body body = BodyComponent.MAPPER.get(entity).getBody();

		if(mc.isAccelerateForward()) {			
			float addedVelocity = mc.getLinearAcceleration() * PhysicsSystem.PHYSICS_TIMESTEP;
			float maxAddedVelocity = mc.getMaxLinearVelocity() - body.getLinearVelocity().len();
			float actualAddedVelocity = Math.min(addedVelocity, maxAddedVelocity);
			
			float addForce = body.getMass() * actualAddedVelocity / PhysicsSystem.PHYSICS_TIMESTEP;	// F = ma
			Vector2 addForceVector = VectorMath.forceToVector(addForce, body.getAngle());
			
			events.add(new LinearForceEvent(body, addForceVector));
		}
		
	}
}
