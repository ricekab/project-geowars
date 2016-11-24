package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AIComponent implements Component, Poolable {

	public static final ComponentMapper<AIComponent> MAPPER = ComponentMapper.getFor(AIComponent.class);

	private Body target;

	public Body getTarget() {
		return target;
	}

	public void setTarget(Body target) {
		this.target = target;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
