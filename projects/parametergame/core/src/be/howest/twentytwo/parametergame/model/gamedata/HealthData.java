package be.howest.twentytwo.parametergame.model.gamedata;

import java.util.Observable;

public class HealthData extends Observable {
	private float health;

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
		notifyObservers();
	}
}
