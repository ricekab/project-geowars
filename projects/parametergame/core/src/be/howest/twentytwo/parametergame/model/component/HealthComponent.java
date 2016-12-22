package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class HealthComponent implements Component, Poolable {

	public static final ComponentMapper<HealthComponent> MAPPER = ComponentMapper
			.getFor(HealthComponent.class);

	private float health;


	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void reset() {
		
	}
}
