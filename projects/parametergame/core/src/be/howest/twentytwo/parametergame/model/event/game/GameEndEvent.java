package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class GameEndEvent implements IEvent {

	@Override
	public EventEnum getType() {
		return EventEnum.GAME_END;
	}

}
