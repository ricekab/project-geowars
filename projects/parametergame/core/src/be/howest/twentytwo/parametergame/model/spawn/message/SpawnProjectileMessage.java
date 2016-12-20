package be.howest.twentytwo.parametergame.model.spawn.message;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;

public class SpawnProjectileMessage implements ISpawnMessage {

	private final String type;
	private final Vector2 pos;
	private final Vector2 vel;
	private final float rotation;
	private final short physicsCategory;
	private final short physicsMask;

	public SpawnProjectileMessage(String name, Vector2 pos, Vector2 vel, float rotation, short category, short mask) {
		this.type = name;
		this.pos = pos;
		this.vel = vel;
		this.rotation = rotation;
		this.physicsCategory = category;
		this.physicsMask = mask;
	}

	@Override
	public String getType() {
		return type;
	}

	public Vector2 getPos() {
		return pos;
	}

	public Vector2 getVel() {
		return vel;
	}

	public float getRotation() {
		return rotation;
	}

	public short getPhysicsCategory() {
		return physicsCategory;
	}

	public short getPhysicsMask() {
		return physicsMask;
	}

	@Override
	public void execute(ISpawnFactory factory) {
		factory.spawnEntity(getPos(), getRotation(), getVel(), getPhysicsCategory(), getPhysicsMask());
	}

}
