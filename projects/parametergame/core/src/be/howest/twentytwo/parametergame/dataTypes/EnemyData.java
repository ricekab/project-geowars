package be.howest.twentytwo.parametergame.dataTypes;

public class EnemyData implements EnemyDataI{
	
	private ShipDataI shipData;
	
	public EnemyData(ShipDataI shipData) {
		this.shipData = shipData;
	}

	public ShipDataI getShipData() {
		return shipData;
	}

}
