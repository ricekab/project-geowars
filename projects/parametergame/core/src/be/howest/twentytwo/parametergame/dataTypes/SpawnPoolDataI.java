package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface SpawnPoolDataI {
	
	public ClusterDataI getRandomCluster();
	public Collection<ClusterDataI> getAllClusters();
	public float getSpawnTreshold();
	public float getSpawnTresholdIncrease();
	
	public void addCluster(ClusterDataI cluster);
	public void setSpawnTreshold(float spawnTreshold);
	public void setSpawnTresholdIncrease(float spawnTresholdIncrease);

}
