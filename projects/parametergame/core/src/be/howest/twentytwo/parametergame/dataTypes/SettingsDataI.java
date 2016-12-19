package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Map;

public interface SettingsDataI {
	
	//	GETTERS
	
	public boolean getVolume();
	public float getVolumeLevel();
	public boolean getDamping();
	public float getDampingLevel();
	public Map<String, String> getKeyBinds(UserDataI userDataI);
	
	//	SETTERS	
	
	public void setVolume(boolean volume);
	public void setVolumeLevel(float level);
	public void setDaming(boolean damping);
	public void setDampingLevel(float level);
	public void setKeyBind(UserData user, String key, String value);
	public void addPlayer(UserDataI player);

}
