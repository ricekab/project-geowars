package be.howest.twentytwo.parametergame.model.system;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;


public class AITargetSystem extends IntervalIteratingSystem  {
    
    
    public static final int PRIORITY = 0;

	public AITargetSystem() {
		super(Family.all(AIComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity) {
		AIComponent ai = AIComponent.MAPPER.get(entity);                
		Body aiBody = BodyComponent.MAPPER.get(entity).getBody();
		ai.getTargetBehaviour().killAi(ai);      
	}
		
}
