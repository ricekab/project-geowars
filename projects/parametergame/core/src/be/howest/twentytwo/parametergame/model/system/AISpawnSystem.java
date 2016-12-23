package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.EnemyComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

/**
 * This system is responsible for spawning enemies on the field.
 */
public class AISpawnSystem extends IntervalIteratingSystem {

	public static final int PRIORITY = 0;

	private static final float SPAWN_CHECK_INTERVAL = 0.5f;

	public AISpawnSystem() {
		super(Family.all(EnemyComponent.class).get(), SPAWN_CHECK_INTERVAL, PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity) {
		// TODO Auto-generated method stub

	}

}
