package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PlayerComponent implements Component, Poolable {

	public static final ComponentMapper<PlayerComponent> MAPPER = ComponentMapper
			.getFor(PlayerComponent.class);
	
	

	@Override
	public void reset() {
	}

}
