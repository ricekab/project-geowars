package be.howest.twentytwo.parametergame.service.file;

import java.util.Map;

import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;

/*
 * Reads and saves files with all data regarding the level design.
 */

public interface IFileAccessor {
	//This is the general blueprint for different classes, such as InMemoryFileAccessor, JSONFileAccessor,....	

	public LevelDataI loadLevel(String location);
	public void saveLevel(LevelDataI data, String location);
	
	public SettingsDataI loadSettings(String location);
	public void saveSettings(SettingsDataI data, String location);
	
	public Map<String, String> loadKeymap(String location);
	public void saveKeymap(Map<String, String> keymap, String location);

}
