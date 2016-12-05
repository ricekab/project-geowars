package be.howest.twentytwo.parametergame.dataTypes;

public class DroneData implements DroneDataI{
	
	private String id;
	private int offenseUpgradeLevel;
	private int utilityUpgradeLevel;
	
	public DroneData(String id, int offenseUpgradeLevel, int utilityUpgradeLevel) {
		setID(id);
		setOffenseUpgradeLevel(offenseUpgradeLevel);
		setUtilityUpgradeLevel(utilityUpgradeLevel);
	}
	
	//	SETTERS
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setOffenseUpgradeLevel(int offenseUpgradeLevel) {
		this.offenseUpgradeLevel = offenseUpgradeLevel;
	}
	
	public void setUtilityUpgradeLevel(int utilityUpgradeLevel) {
		this.utilityUpgradeLevel = utilityUpgradeLevel;
	}
	
	//	GETTERS

	@Override
	public String getID() {
		return id;
	}

	@Override
	public int getOffenseUpgradeLevel() {
		return offenseUpgradeLevel;
	}

	@Override
	public int getUtilityUpgradeLevel() {
		return utilityUpgradeLevel;
	}

}
