package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementSystem extends IteratingSystem {
	
	public final static int PRIORITY = 0;

	public MovementSystem(Family family) {
		super(Family.all(MovementComponent.class, BodyComponent.class).get(), PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent mc = MovementComponent.MAPPER.get(entity);
		Body body = BodyComponent.MAPPER.get(entity).getBody();
		
		if(mc.isAccelerateForward()){
			// accel forward action
		}
		
	}

}
