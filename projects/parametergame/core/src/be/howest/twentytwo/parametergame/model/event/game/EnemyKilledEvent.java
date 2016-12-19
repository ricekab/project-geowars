package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

public class EnemyKilledEvent extends BaseGameEvent {

	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_KILLED;
	}

}
