package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.component.AIComponent;

public class AIShootSystem extends IntervalIteratingSystem {
	
	public static final int PRIORITY = 0;

	public AIShootSystem() {
		super(Family.all(AIComponent.class, WeaponComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity) {
		AIComponent ai = AIComponent.MAPPER.get(entity);
                WeaponComponent weapon = WeaponComponent.MAPPER.get(entity);
		Body aiBody = BodyComponent.MAPPER.get(entity).getBody();
		ai.getShootBehaviour().shoot(aiBody, weapon, ai.getTarget().getPosition());
	}

}
