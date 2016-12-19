package be.howest.twentytwo.parametergame.dataTypes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input;

import be.howest.twentytwo.parametergame.input.Inputs;

public class SettingsData implements SettingsDataI, Serializable{
	
	private boolean volume;
	private float volumeLevel;
	private boolean damping;
	private float dampingLevel;
	private Map<UserData, Map<String, String>> userKeys;
	/*
	private Map<String, String> keybinds;
	/*up, down, left, right, primeFire, secondaryFire, cycleSecondary, toggleDamping, menuButton, cheats*/

	public SettingsData(UserData user) {
		this.userKeys = new HashMap<>();
		addPlayer(user);
	}
	
	public void resetControls(UserData user) {
		Map<String, String> keybinds = userKeys.get(user);
		keybinds.put("Z", Inputs.ACCELERATE_FORWARD);
		keybinds.put("S", Inputs.ACCELERATE_BACKWARD);
		keybinds.put("Q", Inputs.TURN_LEFT);
		keybinds.put("D", Inputs.TURN_RIGHT);
		keybinds.put("A", Inputs.FIRE_PRIMARY);
		keybinds.put("E", Inputs.FIRE_SECONDARY);
		keybinds.put("R", Inputs.CYClE_SECONDARY);
		keybinds.put("F", Inputs.TOGGLE_LINEAR_DAMP);
		keybinds.put("Escape", Inputs.OPEN_MENU);
		keybinds.put("C", Inputs.TOGGLE_CHEATS);
	}
	
	//	METHODS	
	

	public void addPlayer(UserData player) {
		HashMap<String, String> keybinds = new HashMap<>();
		userKeys.put(player, keybinds);
		resetControls(player);
		
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

	public Map<String, String> getKeyBinds(UserData user) {
		return userKeys.get(user);
	}

	
	//	SETTERS
	
	
	public void setVolume(boolean volume) {
		this.volume = volume;		
	}

	public void setVolumeLevel(float level) {
		this.volumeLevel = level;
	}

	public void setDaming(boolean damping) {
		this.damping = damping;
	}

	public void setDampingLevel(float level) {
		this.dampingLevel = level;
	}

	public void setKeyBind(UserData user, String key, String value) {
		Map<String, String> keybinds = userKeys.get(user);
		keybinds.put(key, value);
	}
	
}
