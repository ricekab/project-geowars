package be.howest.twentytwo.parametergame.model.event.game;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.IEvent;

import com.badlogic.ashley.core.Entity;

public class WeaponFiredEvent implements IEvent {

	/** Entity that fired the weapon. */
	private Entity source;
	private WeaponDataI weapon;

	public WeaponFiredEvent(Entity source, WeaponDataI weapon) {
		this.source = source;
		this.weapon = weapon;
	}

	public Entity getSource() {
		return source;
	}

	public String getWeaponName() {
		return weapon.getID();
	}
	
	public WeaponDataI getWeaponData(){
		return weapon;
	}

	@Override
	public EventEnum getType() {
		return EventEnum.WEAPON_FIRED;
	}

}
