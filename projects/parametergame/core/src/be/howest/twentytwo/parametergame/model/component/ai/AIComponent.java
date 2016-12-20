package be.howest.twentytwo.parametergame.model.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

import be.howest.twentytwo.parametergame.model.ai.AIMoveBehaviour;

public class AIComponent implements Component, Poolable {

	public static final ComponentMapper<AIComponent> MAPPER = ComponentMapper.getFor(AIComponent.class);

	private Body target;
	private AIMoveBehaviour moveBehaviour;

	public Body getTarget() {
		return target;
	}

	public void setTarget(Body target) {
		this.target = target;
	}

	public AIMoveBehaviour getMoveBehaviour() {
		return moveBehaviour;
	}

	public void setMoveBehaviour(AIMoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
