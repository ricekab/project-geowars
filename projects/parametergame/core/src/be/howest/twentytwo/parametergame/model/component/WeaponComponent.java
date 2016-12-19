package be.howest.twentytwo.parametergame.model.component;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class WeaponComponent implements Component, Poolable {

	public static final ComponentMapper<WeaponComponent> MAPPER = ComponentMapper.getFor(WeaponComponent.class);

	private WeaponDataI primaryWeapon;
	private WeaponDataI secondaryWeapons[];
	private int activeSecondaryWeapon;

	@Override
	public void reset() {
	}

}
