package be.howest.twentytwo.parametergame.model.component;

import be.howest.twentytwo.parametergame.model.gamedata.HealthData;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class HealthComponent implements Component, Poolable {

	public static final ComponentMapper<HealthComponent> MAPPER = ComponentMapper
			.getFor(HealthComponent.class);

	private HealthData health;


	public HealthData getHealthData() {
		return health;
	}

	public void setHealthData(HealthData health) {
		this.health = health;
	}

	@Override
	public void reset() {
		
	}
}
