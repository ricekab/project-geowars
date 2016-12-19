package be.howest.twentytwo.parametergame.dataTypes;


public interface SettingsDataI {
	
	//	GETTERS
	
	public boolean getVolume();
	public float getVolumeLevel();
	public boolean getDamping();
	public float getDampingLevel();
	public String getKeyBind(String key);
	
	//	SETTERS	
	
	public void setVolume(boolean volume);
	public void setVolumeLevel(float level);
	public void setDaming(boolean damping);
	public void setDampingLevel(float level);
	public void setKeyBind(String key, String value);

}
