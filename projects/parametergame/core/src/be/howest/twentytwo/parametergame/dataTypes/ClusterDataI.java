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
	public int getAmountStored();
	public float getDelay();
	public int getGroups();
	public int getEnemies();
	public float getWeight();
	public String getEnemyName();

}
