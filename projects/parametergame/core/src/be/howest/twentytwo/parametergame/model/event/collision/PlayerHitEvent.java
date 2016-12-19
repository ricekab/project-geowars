package be.howest.twentytwo.parametergame.model.event.collision;

import be.howest.twentytwo.parametergame.model.event.EventEnum;

public class PlayerHitEvent extends BaseCollisionEvent {

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_HIT;
	}

}
