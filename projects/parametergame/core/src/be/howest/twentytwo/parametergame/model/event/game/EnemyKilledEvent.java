package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

import com.badlogic.gdx.math.Vector2;

public class EnemyKilledEvent extends BaseGameEvent {

	private final Vector2 killedAt;
	private final float scoreValue;
	private final float geomDropRate;

	public EnemyKilledEvent(Vector2 deathPosition, float score, float geomDrop) {
		this.killedAt = deathPosition;
		this.scoreValue = score;
		this.geomDropRate = geomDrop;
	}

	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_KILLED;
	}

	public Vector2 getDeathPosition() {
		return killedAt;
	}

	public float getScoreValue() {
		return scoreValue;
	}

	public float getGeomDropRate() {
		return geomDropRate;
	}

}
