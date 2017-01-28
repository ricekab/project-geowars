package be.howest.twentytwo.parametergame.model.ai;


import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BasicAIMoveBehaviour implements IAIMoveBehaviour {
	private float optimalDistance;

	public BasicAIMoveBehaviour(float optimalDistance) {
		this.optimalDistance = optimalDistance;
	}

	@Override
	public float getOptimalDistance() {
		return optimalDistance;
	}

	@Override
	public void calculateMove(MovementComponent movement, Body aiBody, Vector2 target) {
		// Move enemy in direction of target
		// Turn toward player
		Vector2 forwardUnitVector = aiBody.getWorldVector(Vector2.X);

		// Turn enemy to look in player direction
		Vector2 distanceToPlayer = VectorMath.subtract(target, aiBody.getPosition());

		float angleInRad = MathUtils.atan2(distanceToPlayer.y, distanceToPlayer.x)
				- MathUtils.atan2(forwardUnitVector.y, forwardUnitVector.x);
		if (angleInRad < 0) {
			angleInRad += MathUtils.PI * 2;
		}
		float angle = angleInRad * MathUtils.radiansToDegrees;
		if (5f < angle && angle < 180f) {
			movement.setTurnLeft(true);
			movement.setTurnRight(false);
		} else if (180f < angle && angle < 355f) {
			movement.setTurnLeft(false);
			movement.setTurnRight(true);
		} else if (angle < 180f) {
			movement.setTurnLeft(false);
			movement.setTurnRight(false);
		}
		// Set movement forward/backward
		if (angle < 30f || angle > 330f) {
			float actualDistance = distanceToPlayer.len();
			if (actualDistance > optimalDistance + 2.5f) {
				movement.setAccelerateForward(true);
				movement.setAccelerateBackward(false);
			} else if (actualDistance < optimalDistance + 2.5f) {
				movement.setAccelerateForward(false);
				movement.setAccelerateBackward(true);
			} else {
				movement.setAccelerateForward(false);
				movement.setAccelerateBackward(false);
			}
		} else {
			movement.setAccelerateForward(false);
			movement.setAccelerateBackward(false);
		}
	}

}