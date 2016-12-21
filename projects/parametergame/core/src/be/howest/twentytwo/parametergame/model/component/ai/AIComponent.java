package be.howest.twentytwo.parametergame.model.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;

public class AIComponent implements Component, Poolable {

	public static final ComponentMapper<AIComponent> MAPPER = ComponentMapper.getFor(AIComponent.class);

	private Body target;
	private IAIMoveBehaviour moveBehaviour;
        private IAIShootBehaviour shootBehaviour;

	public Body getTarget() {
		return target;
	}

	public void setTarget(Body target) {
		this.target = target;
	}

	public IAIMoveBehaviour getMoveBehaviour() {
		return moveBehaviour;
	}

	public void setMoveBehaviour(IAIMoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}
        
        public IAIShootBehaviour getShootBehaviour() {
		return shootBehaviour;
	}

	public void setShootBehaviour(IAIShootBehaviour shootBehaviour) {
		this.shootBehaviour = shootBehaviour;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
