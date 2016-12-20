package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.ai.AIComponent;

public class AIMovementSystem extends IntervalIteratingSystem {
	
	public static final int PRIORITY = 0;

	public AIMovementSystem() {
		super(Family.all(AIComponent.class, MovementComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity) {
		AIComponent ai = AIComponent.MAPPER.get(entity);
		MovementComponent movement = MovementComponent.MAPPER.get(entity);
		Body aiBody = BodyComponent.MAPPER.get(entity).getBody();
		ai.getMoveBehaviour().calculateMove(movement, aiBody, ai.getTarget().getPosition());
	}

}
