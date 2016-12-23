package be.howest.twentytwo.parametergame.model.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import be.howest.twentytwo.parametergame.model.gamedata.WeaponGameData;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class WeaponComponent implements Component, Poolable {

	public static final ComponentMapper<WeaponComponent> MAPPER = ComponentMapper.getFor(WeaponComponent.class);

	// Input flags
	private boolean firePrimary;
	private boolean fireSecondary;
	private final Collection<WeaponGameData> allWeapons;
	
	private WeaponGameData primaryWeapon;
	private List<WeaponGameData> secondaryWeapons;
	private int activeSecondaryWeapon;
	
	private short physicsCategory;
	private short physicsMask;
	
	public WeaponComponent(){
		this.allWeapons = new ArrayList<WeaponGameData>();
	}

	public WeaponGameData getPrimary() {
		return primaryWeapon;
	}

	public void setPrimary(WeaponGameData primaryWeapon) {
		this.primaryWeapon = primaryWeapon;
		allWeapons.add(primaryWeapon);
	}

	private List<WeaponGameData> getSecondaryWeapons() {
		return secondaryWeapons;
	}

	public void setSecondaryWeapons(List<WeaponGameData> secondaryWeapons) {
		this.secondaryWeapons = secondaryWeapons;
		allWeapons.addAll(secondaryWeapons);
		this.activeSecondaryWeapon = 0;
	}

	public WeaponGameData getActiveSecondaryWeapon() {
		return getSecondaryWeapons().get(activeSecondaryWeapon);
	}

	public Collection<WeaponGameData> getAllWeapons(){
		return this.allWeapons;
	}
	
	public void cycleSecondaryWeapon() {
		activeSecondaryWeapon = (activeSecondaryWeapon + 1) % getSecondaryWeapons().size();
	}

	public boolean isFirePrimary() {
		return firePrimary;
	}

	public void setFirePrimary(boolean firePrimary) {
		this.firePrimary = firePrimary;
	}

	public boolean isFireSecondary() {
		return fireSecondary;
	}

	public void setFireSecondary(boolean fireSecondary) {
		this.fireSecondary = fireSecondary;
	}

	public short getPhysicsCategory() {
		return physicsCategory;
	}

	public void setPhysicsCategory(short physicsCategory) {
		this.physicsCategory = physicsCategory;
	}

	public short getPhysicsMask() {
		return physicsMask;
	}

	public void setPhysicsMask(short physicsMask) {
		this.physicsMask = physicsMask;
	}

	@Override
	public void reset() {
	}

}
