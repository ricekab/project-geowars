package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class PlayerKilledEvent implements IEvent {

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_KILLED;
	}

}
