package be.howest.twentytwo.parametergame.dataTypes;

import java.io.Serializable;

public class ClusterData implements ClusterDataI, Serializable{	
	//a cluster contains only 1 type of enemies. can exist more then once per pool
	
	private float chance;
	private int amountStored;
	private float delay; 	//delay before each group spawns in seconds.
	private int groups;		//groups of enemies spawned in this cluster
	private int enemies;	//amount of enemies spawned per group
	private float weight;	//the weight each enemy ship carries
	
	public ClusterData(float chance, int amountStored, float delay, int groups, int enemies) {
		setChance(chance);
		setAmountStored(amountStored);
		setDelay(delay);
		setGroups(groups);
		setEnemies(enemies);
		setWeight();
	}
	
	public void takeOne() {
		amountStored--;
	}
	
	//	SETTERS	
	
	public void setChance(float chance) {
		this.chance = chance;
	}
	
	public void setAmountStored(int amountStored) {
		this.amountStored = amountStored;
	}
	
	public void setDelay(float delay) {
		this.delay = delay;
	}
	
	public void setGroups(int groups) {
		this.groups = groups;
	}
	
	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}
	
	private void setWeight() {
		this.weight = 1 / (groups * enemies);
	}
	
	//	GETTERS
	
	public float getChance() {
		return chance;
	}
	
	public int getAmountStored() {
		return amountStored;
	}
	
	public float getDelay() {
		return delay;
	}
	
	public int getGroups() {
		return groups;
	}
	
	public int getEnemies() {
		return enemies;
	}
	
	public float getWeight() {
		return weight;
	}

}
