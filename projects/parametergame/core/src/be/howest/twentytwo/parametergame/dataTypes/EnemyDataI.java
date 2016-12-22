package be.howest.twentytwo.parametergame.dataTypes;

public interface EnemyDataI {

	public void setId(String id);
	public void setGeomDropRate(float geomDropRate);
	public void setBaseScore(int baseScore);
	public void setBehaviour(String behaviour);
	public void setShipData(ShipDataI shipData);
	
	public String getId();
	public float getGeomDropRate();
	public int getBaseScore();
	public String getBehaviour();
	public ShipDataI getShipData();
}
