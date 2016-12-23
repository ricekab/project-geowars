package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class EnemySpawnedEvent implements IEvent {

	private final String enemyName;
	private final int amountSpawned;
	
	public EnemySpawnedEvent(String name, int amount) {
		this.enemyName = name;
		this.amountSpawned = amount;
	}
	
	public String getEnemyName() {
		return enemyName;
	}

	public int getAmountSpawned() {
		return amountSpawned;
	}

	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_SPAWNED;
	}

}
