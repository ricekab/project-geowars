package be.howest.twentytwo.parametergame.dataTypes;

public interface ClusterDataI {
	
	public void takeOne();
	
	
	public void setChance(float chance);
	public void setAmountStored(int amountStored);
	public void setDelay(float Delay);
	public void setGroups(int groups);
	public void setEnemies(int enemies);
	public void setEnemyName(String enemyName);
	
	public float getChance();
	/** Amount of groups in this cluster */
	public int getAmountStored();
	/** Delay between spawning of groups (of a single use)*/
	public float getDelay();
	/** Number of groups to spawn (for one cluster use) */
	public int getGroups();
	/** Number of enemies in one group */
	public int getEnemies();
	public float getWeight();
	public String getEnemyName();

}
