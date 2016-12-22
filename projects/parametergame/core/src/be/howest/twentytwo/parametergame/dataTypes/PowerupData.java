package be.howest.twentytwo.parametergame.dataTypes;

public class PowerupData implements PowerupDataI {
	
	private String powerupId;
	private String effectId;
	private int powerupDuration;
	private int powerupLifetime;
	private String effectTpe;
	private int effectStrength;
	
	public PowerupData(String powerupId, String effectId, int powerupDuration, int powerupLifetime, String effectType, int effectStrength) {
		this.powerupId = powerupId;
		this.effectId = effectId;
		this.powerupDuration = powerupDuration;
		this.powerupLifetime = powerupLifetime;
		this.effectTpe = effectType;
		this.effectStrength = effectStrength;
	}
	
	//	GETTERS

	public String getPowerupId() {
		return powerupId;
	}

	public String getEffectId() {
		return effectId;
	}

	public int getPowerupDuration() {
		return powerupDuration;
	}

	public int getPowerupLifetime() {
		return powerupLifetime;
	}

	public String getEffectTpe() {
		return effectTpe;
	}

	public int getEffectStrength() {
		return effectStrength;
	}

}
