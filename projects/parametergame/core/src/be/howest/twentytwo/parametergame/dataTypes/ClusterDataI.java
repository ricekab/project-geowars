package be.howest.twentytwo.parametergame.dataTypes;

public interface ClusterDataI {
	
	public void takeOne();
	
	public float getChance();
	public int getAmountStored();
	public float getDelay();
	public int getGroups();
	public int getEnemies();
	public float getWeight();
	
	public void setChance(float chance);
	public void setAmountStored(int amountStored);
	public void setDelay(float Delay);
	public void setGroups(int groups);
	public void setEnemies(int enemies);

}
