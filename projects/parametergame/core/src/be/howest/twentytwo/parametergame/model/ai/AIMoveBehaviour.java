package be.howest.twentytwo.parametergame.model.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public interface AIMoveBehaviour {

	public float getOptimalDistance();

	/** Modifies the given move component to achieve it's objective. */
	public void calculateMove(MovementComponent movement, Body ai, Vector2 target);

}
