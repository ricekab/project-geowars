package be.howest.twentytwo.parametergame.model.event.game;

import com.badlogic.ashley.core.Entity;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

public class WeaponFiredEvent implements IEvent {

	/** Entity that fired the weapon. */
	private Entity source;
	private WeaponDataI weapon;
	
	public WeaponFiredEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public EventEnum getType() {
		return EventEnum.WEAPON_FIRED;
	}

}
