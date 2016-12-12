package be.howest.twentytwo.parametergame.dataTypes;

import java.util.HashMap;
import java.util.Map;

public class SettingsData implements SettingsDataI{
	
	private boolean volume;
	private float volumeLevel;
	private boolean damping;
	private float dampingLevel;
	private Map<String, String> keybinds;
	/*up, down, left, right, primeFire, secondaryFire, cycleSecondary, toggleDamping, menuButton, cheats*/

	public SettingsData() {
		this.keybinds = new HashMap<>();
		resetControls();
	}
	
	public void resetControls() {
		keybinds.put("up", "z");
		keybinds.put("down", "s");
		keybinds.put("left", "q");
		keybinds.put("right", "d");
		keybinds.put("primaryFire", "a");
		keybinds.put("secondaryFire", "e");
		keybinds.put("cycleSecondary", "r");
		keybinds.put("toggleDamping", "f");
		keybinds.put("menuButton", "esc");
		keybinds.put("cheats", "c");
	}

	
	//	GETTERS
	
	
	public boolean getVolume() {
		return volume;
	}

	public float getVolumeLevel() {
		return volumeLevel;
	}

	public boolean getDamping() {
		return damping;
	}

	public float getDampingLevel() {
		return dampingLevel;
	}

	public String getKeyBind(String key) {
		return keybinds.get(key);
	}

	
	//	SETTERS
	
	
	public void setVolume(boolean volume) {
		this.volume = volume;		
	}

	@Override
	public void setVolumeLevel(float level) {
		this.volumeLevel = level;
	}

	public void setDaming(boolean damping) {
		this.damping = damping;
	}

	public void setDampingLevel(float level) {
		this.dampingLevel = level;
	}

	public void setKeyBind(String key, String value) {
		keybinds.put(key, value);
	}
	
}
