package be.howest.twentytwo.parametergame.model.gamedata;

import java.util.Observable;

public class PlayerData extends Observable{
	private float score;
	private float geomRadius;

	public float getScore() {
		return score;
	}

	public float getGeomRadius() {
		return geomRadius;
	}

	public void setScore(float score) {
		this.score = score;
		notifyObservers();
	}

	public void setGeomRadius(float geomRadius) {
		this.geomRadius = geomRadius;
		notifyObservers();
	}
}
