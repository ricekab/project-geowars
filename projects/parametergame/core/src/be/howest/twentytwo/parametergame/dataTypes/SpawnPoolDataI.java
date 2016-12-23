package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface SpawnPoolDataI {
	/**
	 * Picks one random cluster from the spawnpool, based on the chance it has to spawn.
	 * The amount of times it is stored does not influence the chance it has to spawn,
	 * but if there are none left, that cluster gets removed, and the others indirectly
	 * have more chance to get spawned.
	 * @return A random cluster, based on it's chance to spawn
	 */
	public ClusterDataI getRandomCluster();
	public Collection<ClusterDataI> getAllClusters();
	public float getSpawnTreshold();
	public float getSpawnTresholdIncrease();
	
	public void addCluster(ClusterDataI cluster);
	public void setSpawnTreshold(float spawnTreshold);
	public void setSpawnTresholdIncrease(float spawnTresholdIncrease);
	
	public boolean isEmpty();

}
