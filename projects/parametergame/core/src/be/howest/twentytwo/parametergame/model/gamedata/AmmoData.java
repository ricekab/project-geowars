package be.howest.twentytwo.parametergame.model.gamedata;

import java.util.Observable;

public class AmmoData extends Observable {
	private int ammoCount;
	/** Time (in seconds) for it to cool down between shots. */
	private float cooldownTime;
	private float currentCooldown;
	
	public AmmoData(int amount, float cooldown){
		setAmmoCount(ammoCount);
		setCooldownTme(cooldown);
		setCurrentCooldown(0f);
	}

	public float getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(float cd) {
		this.currentCooldown = cd;
		notifyObservers();
	}

	public float getCooldownTme() {
		return cooldownTime;
	}

	private void setCooldownTme(float cooldownTme) {
		this.cooldownTime = cooldownTme;
	}

	public int getAmmoCount() {
		return ammoCount;
	}

	public void setAmmoCount(int ammoCount) {
		this.ammoCount = ammoCount;
		notifyObservers();
	}
}
