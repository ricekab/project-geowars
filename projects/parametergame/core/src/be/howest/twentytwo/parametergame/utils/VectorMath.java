package be.howest.twentytwo.parametergame.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public final class VectorMath {

	/**
	 * Return the smaller of the 2 vectors based on the vector's length. The
	 * vector closest to 0 is returned.
	 */
	public static Vector2 min(Vector2 a, Vector2 b) {
		return a.len2() < b.len2() ? a : b;
	}

	/**
	 * Return the larger of the 2 vectors based on the vector's length. The
	 * vector closest to positive infinity is returned.
	 */
	public static Vector2 max(Vector2 a, Vector2 b) {
		return a.len2() > b.len2() ? a : b;
	}

	/**
	 * Calculates the forward force vector for the given (box2d) angle with
	 * given force or impulse.
	 */
	public static Vector2 forceToForwardVector(float force, float angle) {
		return new Vector2(force * MathUtils.cos(angle + MathUtils.PI / 2),
				force * MathUtils.sin(angle + MathUtils.PI / 2));
	}
	
	/**
	 * Calculates the forward force vector for the given (box2d) body with
	 * given force or impulse.
	 */
	public static Vector2 forceToForwardVector(float force, Body body) {
		return new Vector2(body.getWorldVector(Vector2.X)).scl(force);
	}
}
