package be.howest.twentytwo.parametergame.dataTypes;

public class ClusterData {
	
	private float chance;
	private int amountStored;
	
	public float getChance() {
		return chance;
	}
	
	public int getAmountStored() {
		return amountStored;
	}
	
	public void takeOne() {
		amountStored--;
	}

}
