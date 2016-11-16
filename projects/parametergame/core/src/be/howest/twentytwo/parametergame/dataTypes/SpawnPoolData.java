package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Set;
import java.util.Map;
import java.util.Random;

public class SpawnPoolData {	//a collection of enemy clusters. they spawn per cluster, in a random order
	
	private Set<ClusterData> clusters;
	private float spawnTreshold;
	private float spawnTresholdIncrease;
	
	private ClusterData selectRandomCluster() {
		//TODO initialization so the return doesn't cry. if it actually returns null, it's broken. this needs to be changed.
		ClusterData selectedCluster = null;
		float totalChance = 0f;
		for(ClusterData c : clusters) {
			totalChance += c.getChance();	//total chance will be calculated, by adding each cluster's chance.
		}
		
		float random = (float)Math.random();	//pick a random value, indicating what cluster will get chosen.
		random = random * totalChance;			//scale the random value to the total chance.
		
		float cumulativeChance = 0f;			//This is the lower bound of the range we'll check.
		
		for(ClusterData c : clusters) {
			if(random < cumulativeChance + c.getChance()) {	//here we check if the random value is inbetween the boundaries. random will always be larger then the cumulativeChance value.
				selectedCluster = c;	//if random is lesser then cummulativeChance + chance of c, we got this one as the random cluster.
			} else {
				cumulativeChance += c.getChance(); //random was larger then the higher bound of this clusters chance. the lower bound of the next cluster is set to the upper bound of this cluster.
			}
		}
		return selectedCluster;
	}
	
	private void reduceAmount(ClusterData cluster) {
		cluster.takeOne();
		if(cluster.getAmountStored() < 1) {
			clusters.remove(cluster);
		}
	}
	
	//	GETTERS
	
	public ClusterData getRandomCluster() {
		ClusterData randomCluster = selectRandomCluster();
		reduceAmount(randomCluster);
		return randomCluster;
	}
	
	public float getSpawnTreshold() {
		return spawnTreshold;
	}
	
	public float getSpawnTresholdIncrease() {
		return spawnTresholdIncrease;
	}
	
	//	SETTERS
	
	public void addCluster(ClusterData cluster) {
		clusters.add(cluster);
	}
	
	public void setSpawnTreshold(float spawnTreshold) {
		this.spawnTreshold = spawnTreshold;
	}
	
	public void setSpawnTresholdIncrease(float spawnTresholdIncrease) {
		this.spawnTresholdIncrease = spawnTresholdIncrease;
	}

}
