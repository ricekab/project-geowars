package be.howest.twentytwo.parametergame.dataTypes;

public interface DroneDataI {
	
	public String getID();
	
	public int getOffenseUpgradeLevel();
	public int getUtilityUpgradeLevel();
	
	public void setOffenseUpgradeLevel(int lvl);
	public void setUtilityUpgradeLevel(int lvl);

}
