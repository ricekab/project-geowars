package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class HealthSystem extends IteratingSystem {

	public static final int PRIORITY = 0;

	public HealthSystem() {
		super(Family.all(HealthComponent.class, BodyComponent.class).get(), PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		HealthComponent hp = HealthComponent.MAPPER.get(entity);
		if(hp.getHealth() < VectorMath.EPSILON){
			// TODO: Possibly not needed --> event system?
		}
	}

}
