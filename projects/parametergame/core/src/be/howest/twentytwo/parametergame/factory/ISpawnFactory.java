package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public interface ISpawnFactory {

	/**
	 * Creates an entity at the given location with given rotation and initial velocity. Sets its
	 * fixtures to the given category and mask when possible. Note that this affects all use of
	 * {@link #spawnEntity(Vector2, float, Vector2, short, short)} as well!
	 * 
	 */
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity,
			short physicsCategory, short physicsMask);

	/**
	 * Creates an entity at the given location with geven rotation and initial velocity. Physics
	 * category and mask will be used from last known change.
	 */
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity);

	/** Uniquely identifies this spawn factory. */
	public String getType();
}
