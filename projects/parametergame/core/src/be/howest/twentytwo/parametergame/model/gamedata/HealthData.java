package be.howest.twentytwo.parametergame.model.gamedata;

import java.util.Observable;

public class HealthData extends Observable {
	private float health;
	private boolean invulnerable;

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		if(!isInvulnerable()){
			this.health = health;
			notifyObservers();
		}
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
}
