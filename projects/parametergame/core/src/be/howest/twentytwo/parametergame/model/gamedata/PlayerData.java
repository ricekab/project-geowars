package be.howest.twentytwo.parametergame.model.gamedata;

import java.util.Observable;

public class PlayerData extends Observable{
	private float score;
	private float geomRadius;

	public PlayerData(float score, float geomRadius){
		this.score = score;
		this.geomRadius = geomRadius;
	}
	
	public PlayerData(float geomRadius){
		this(0f, geomRadius);
	}
	
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
