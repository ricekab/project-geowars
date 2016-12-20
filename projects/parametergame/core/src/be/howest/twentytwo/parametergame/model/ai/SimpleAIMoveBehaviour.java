package be.howest.twentytwo.parametergame.model.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.message.LinearForceMessage;
import be.howest.twentytwo.parametergame.screen.GameScreen;
import be.howest.twentytwo.parametergame.utils.VectorMath;

public class SimpleAIMoveBehaviour implements AIMoveBehaviour {

	private float optimalDistance;

	public SimpleAIMoveBehaviour(float optimalDistance) {
		this.optimalDistance = optimalDistance;
	}

	@Override
	public float getOptimalDistance() {
		return optimalDistance;
	}

	@Override
	public void calculateMove(MovementComponent movement, Body aiBody, Vector2 target) {
		// TODO: COPIED HERE

		// Move enemy in direction of target
		// Turn toward player
		Vector2 forwardUnitVector = aiBody.getWorldVector(Vector2.X);

		// Turn enemy to look in player direction
		Vector2 distanceToPlayer = VectorMath.subtract(target, aiBody.getPosition());
		float cosineAngle = forwardUnitVector.dot(distanceToPlayer)
				/ (forwardUnitVector.len() * distanceToPlayer.len());
		float angle = MathUtils.cos(cosineAngle) * MathUtils.radiansToDegrees;
		if (angle < 2.5f) {
			movement.setTurnLeft(false);
			movement.setTurnRight(false);
		} else if (angle < 180f) {
			movement.setTurnLeft(false);
			movement.setTurnRight(true);
		} else if (angle < 357.5f) {
			movement.setTurnLeft(true);
			movement.setTurnRight(false);
		} else {
			movement.setTurnLeft(false);
			movement.setTurnRight(false);
		}

		// Set movement forward/backward
		if (angle < 30f || angle > 330f) {
			float actualDistance = distanceToPlayer.len();
			if (actualDistance < optimalDistance) {
				movement.setAccelerateForward(true);
				movement.setAccelerateBackward(false);
			}else {
				movement.setAccelerateForward(false);
				movement.setAccelerateBackward(true);
			}
		} else {
			movement.setAccelerateForward(false);
		}

		// END COPY
	}

}
