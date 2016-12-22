package be.howest.twentytwo.parametergame.model.ai;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class NullAIMoveBehaviour implements IAIMoveBehaviour {

	@Override
	public float getOptimalDistance() {
		return 0f;
	}

	@Override
	public void calculateMove(MovementComponent movement, Body ai, Vector2 target) {
		return;
	}

}
