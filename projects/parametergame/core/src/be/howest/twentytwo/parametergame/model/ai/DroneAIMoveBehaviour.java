package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe
/*
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class DroneAIMoveBehaviour implements IAIMoveBehaviour{
    private float optimalDistance;

	public DroneAIMoveBehaviour(float optimalDistance) {
		this.optimalDistance = optimalDistance;
	}

	@Override
	public float getOptimalDistance() {
		return optimalDistance;
	}

	@Override
	public void calculateMove(MovementComponent movement, Body aiBody, Vector2 target) {
                //Drone only rotates!!!
		// Move enemy in direction of target
		// Turn toward target
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
        }
}*/
