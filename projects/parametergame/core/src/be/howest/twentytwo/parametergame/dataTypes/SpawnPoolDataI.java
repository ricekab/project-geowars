package be.howest.twentytwo.parametergame.dataTypes;

public interface SpawnPoolDataI {
	
	public ClusterDataI getRandomCluster();
	public float getSpawnTreshold();
	public float getSpawnTresholdIncrease();
	
	public void addCluster(ClusterDataI cluster);
	public void setSpawnTreshold(float spawnTreshold);
	public void setSpawnTresholdIncrease(float spawnTresholdIncrease);

}
