package be.howest.twentytwo.parametergame.dataTypes;

public interface SpawnPoolDataI {
	
	public ClusterData getRandomCluster();
	public float getSpawnTreshold();
	public float getSpawnTresholdIncrease();
	
	public void addCluster(ClusterData cluster);
	public void setSpawnTreshold(float spawnTreshold);
	public void setSpawnTresholdIncrease(float spawnTresholdIncrease);

}
