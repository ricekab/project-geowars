package be.howest.twentytwo.parametergame.dataTypes;

public class EnemyData implements EnemyDataI{
	
	private String id;
	private float geomDropRate;
	private int baseScore;
	private String behaviour;
	private ShipDataI shipData;
	
	@Deprecated
	public EnemyData(ShipDataI shipData) {
		this.shipData = shipData;
	}
	
	public EnemyData(String id, float geomDropRate, int baseScore, String behaviour, ShipDataI shipdata) {
		setId(id);
		setGeomDropRate(geomDropRate);
		setBaseScore(baseScore);
		setBehaviour(behaviour);
		setShipData(shipdata);
	}
	
	
	//	SETTERS
	

	public void setId(String id) {
		this.id = id;
	}

	public void setGeomDropRate(float geomDropRate) {
		this.geomDropRate = geomDropRate;
	}

	public void setBaseScore(int baseScore) {
		this.baseScore = baseScore;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public void setShipData(ShipDataI shipData) {
		this.shipData = shipData;
	}
	
	
	//	GETTERS

	
	public String getId() {
		return id;
	}

	public float getGeomDropRate() {
		return geomDropRate;
	}

	public int getBaseScore() {
		return baseScore;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public ShipDataI getShipData() {
		return shipData;
	}

}
