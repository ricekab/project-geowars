package be.howest.twentytwo.parametergame.model.ai;

import javax.swing.plaf.ActionMapUIResource;

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
		// Move enemy in direction of target
		// Turn toward player
		Vector2 forwardUnitVector = aiBody.getWorldVector(Vector2.X);

		// Turn enemy to look in player direction
		Vector2 distanceToPlayer = VectorMath.subtract(target, aiBody.getPosition());

		System.out.println(forwardUnitVector.toString());
		System.out.println(distanceToPlayer.toString());

		float angleInRad = MathUtils.atan2(distanceToPlayer.y, distanceToPlayer.x)
				- MathUtils.atan2(forwardUnitVector.y, forwardUnitVector.x);
		if (angleInRad < 0) {
			angleInRad += MathUtils.PI * 2;
		}
		float angle = angleInRad * MathUtils.radiansToDegrees;
		System.out.println("angle (rad): " + angleInRad);
		System.out.println("angle: " + angle);
		if (5f < angle && angle < 180f) {
			System.out.println("Turn left");
			movement.setTurnLeft(true);
			movement.setTurnRight(false);
		} else if (180f < angle && angle < 355f) {
			movement.setTurnLeft(false);
			movement.setTurnRight(true);
			System.out.println("Turn right");
		} else if (angle < 180f) {
			System.out.println("don't turn");
			movement.setTurnLeft(false);
			movement.setTurnRight(false);
		}
		// Set movement forward/backward
		if (angle < 30f || angle > 330f) {
			float actualDistance = distanceToPlayer.len();
			System.out.println("OPTIMAL: " + optimalDistance);
			System.out.println("ACTUAL: " + actualDistance);
			if (actualDistance > optimalDistance + 2.5f) {
				System.out.println("move forward");
				movement.setAccelerateForward(true);
				movement.setAccelerateBackward(false);
			} else if (actualDistance < optimalDistance + 2.5f) {
				System.out.println("move back");
				movement.setAccelerateForward(false);
				movement.setAccelerateBackward(true);
			} else {
				System.out.println("stop");
				movement.setAccelerateForward(false);
				movement.setAccelerateBackward(false);
			}
		} else {
			System.out.println("stop");
			movement.setAccelerateForward(false);
			movement.setAccelerateBackward(false);
		}
	}

}
