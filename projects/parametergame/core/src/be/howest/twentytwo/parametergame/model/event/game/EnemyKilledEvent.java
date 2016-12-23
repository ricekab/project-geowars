package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.model.event.EventEnum;

public class EnemyKilledEvent extends BaseGameEvent {

	private EnemyDataI enemy;
	
	public EnemyKilledEvent() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_KILLED;
	}

}
