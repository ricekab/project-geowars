package be.howest.twentytwo.parametergame.model.spawn.message;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

import com.badlogic.gdx.math.Vector2;

public class SpawnGeomMessage implements ISpawnMessage {

	private static float RANDOM_X_OFFSET = 5;
	private static float RANDOM_Y_OFFSET = 5;

	private final Vector2 pos;
	private final int amount;

	public SpawnGeomMessage(Vector2 pos, int amount) {
		this.pos = pos;
		this.amount = amount;
	}

	@Override
	public String getType() {
		return "geom"; // HARD CODE
	}

	@Override
	public void execute(ISpawnFactory factory) {
		Vector2 spawnPos;
		for (int i = 0; i < amount; i++) {
			spawnPos = new Vector2(getPos().x + (float) Math.random() * RANDOM_X_OFFSET * 2
					- RANDOM_X_OFFSET, getPos().y + (float) Math.random() * RANDOM_Y_OFFSET * 2
					- RANDOM_Y_OFFSET);

			factory.spawnEntity(spawnPos, (float) (Math.random() * Math.PI * 2), new Vector2(0, 0),
					Collision.PLAYER_PICKUPS, Collision.PICKUP_MASK);
		}

	}

	public Vector2 getPos() {
		return pos;
	}

}
